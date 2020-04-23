package com.codepirates.securitydesk.service;

import com.codepirates.securitydesk.entity.ExcelEmployeeList;
import com.codepirates.securitydesk.entity.MasterEmployee;
import com.codepirates.securitydesk.entity.MasterJobRole;
import com.codepirates.securitydesk.repository.AdminRepositoryImpl;
import com.codepirates.securitydesk.repository.JobRoleImpl;
import com.codepirates.securitydesk.repository.SuperRepository;
import com.codepirates.securitydesk.model.AdminModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    SuperRepository adminrepo;

    @Autowired
    JobRoleImpl jobRoleImpl;

    @Autowired
    AdminRepositoryImpl adminRepositoryImpl;

    private static SecretKeySpec secretKey;
    private static byte[] key;
    final String secret = "ssshhhhhhhhhhh!!!!";

    public boolean saveUser(AdminModel user) {

        if (validateUser(user)) {
            MasterEmployee employee = adminRepositoryImpl.findByEmpId(user.getId());

            for (MasterJobRole test : employee.getRoles()) {
                if (test.getJobRoleName().equals(user.getJobs())) {
                    return true;
                }
            }

            employee.setPassword(encrypt(user.getPassword(), secret));
            MasterJobRole job = jobRoleImpl.findByJobName(user.getJobs());
            Set<MasterJobRole> roles = employee.getRoles();
            roles.add(job);
            employee.setRoles(roles);
            adminRepositoryImpl.save(employee);
            return false;

        } else {
            MasterEmployee masterEmployee = new MasterEmployee();
            masterEmployee.setEmail(user.getEmail());
            masterEmployee.setEmpId(user.getId());
            masterEmployee.setLocation(user.getLocation());
            String encryptedPassword = encrypt(user.getPassword(), secret);
            masterEmployee.setPassword(encryptedPassword);
            masterEmployee.setEmpname(user.getName());
            Set<MasterJobRole> finalRoles = new HashSet<MasterJobRole>();
            finalRoles.add(jobRoleImpl.findByJobName(user.getJobs()));
            masterEmployee.setRoles(finalRoles);
            adminRepositoryImpl.save(masterEmployee);
        }
        return false;
    }

    public boolean validateUser(AdminModel user) {

        List<MasterEmployee> allUsers = adminrepo.fetchMasterEmployee();

        for (MasterEmployee test : allUsers) {
            if ((test.getEmpId().equals(user.getId())) && (test.getDeleteStatus() != 1)) {
                return true;
            }
        }
        return false;
    }

    public static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String strToEncrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public boolean deleteUser(List<AdminModel> requestData) {

        return adminrepo.deleteUserDB(requestData);
    }

    public List<MasterEmployee> searchUser() {

        return adminrepo.searchUserDB();
    }

    @SuppressWarnings("unused")
    public List<AdminModel> searchManagerService(AdminModel requestData) {
        List<MasterEmployee> allEmployee = adminrepo.fetchMasterEmployee();
        List<MasterEmployee> finalEmployees = new ArrayList<MasterEmployee>();
        List<AdminModel> responseData = new ArrayList<AdminModel>();
        for (MasterEmployee test : allEmployee) {
            if ((test.getLocation()).equals(requestData.getLocation())) {

                int check = test.getDeleteStatus();

                Set<MasterJobRole> roles = test.getRoles();
                for (MasterJobRole test2 : roles) {
                    if (test2.getJobRoleName().equals(requestData.getJobs())) {

                        AdminModel oneData = new AdminModel();
                        oneData.setEmail(test.getEmail());
                        oneData.setId(test.getEmpId());
                        oneData.setSno(test.getId());
                        oneData.setName(test.getEmpname());
                        oneData.setJob(requestData.getJobs());
                        oneData.setLocation(requestData.getLocation());
                        responseData.add(oneData);
                    }
                }
            }
        }
        return responseData;
    }

    public List<ExcelEmployeeList> searchAllUserExcel() {

        return adminrepo.listAllUsersExcel();
    }

    public List<AdminModel> listManagersService() {
        List<MasterEmployee> employees = adminrepo.fetchMasterEmployee();
        List<AdminModel> responseData = new ArrayList<AdminModel>();

        for (MasterEmployee test : employees) {
            if (test.getDeleteStatus() != 1) {
                for (MasterJobRole test2 : test.getRoles()) {
                    AdminModel oneData = new AdminModel();
                    oneData.setSno(test.getId());
                    oneData.setId(test.getEmpId());
                    oneData.setEmail(test.getEmail());
                    oneData.setLocation(test.getLocation());
                    oneData.setJob(test2.getJobRoleName());
                    oneData.setName(test.getEmpname());
                    ;
                    responseData.add(oneData);
                }
            }
        }
        return responseData;
    }
}
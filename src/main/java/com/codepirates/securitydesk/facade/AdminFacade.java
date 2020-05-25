package com.codepirates.securitydesk.facade;

import com.codepirates.securitydesk.entity.MasterEmployee;
import com.codepirates.securitydesk.entity.MasterJobRole;
import com.codepirates.securitydesk.model.Admin;
import com.codepirates.securitydesk.repository.AdminRepository;
import com.codepirates.securitydesk.repository.JobRole;
import com.codepirates.securitydesk.service.SuperService;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Component
public class AdminFacade {

    private final SuperService superService;
    private final JobRole jobRole;
    private final AdminRepository adminRepository;

    private static SecretKeySpec secretKey;
    private static byte[] key;
    final String secret = "ssshhhhhhhhhhh!!!!";

    public AdminFacade(SuperService superService, JobRole jobRole, AdminRepository adminRepository) {
        this.superService = superService;
        this.jobRole = jobRole;
        this.adminRepository = adminRepository;
    }

    public boolean saveUser(Admin user) {

        if (validateUser(user)) {
            MasterEmployee employee = adminRepository.findByEmpId(user.getId());

            for (MasterJobRole test : employee.getRoles()) {
                if (test.getJobRoleName().equals(user.getJobs())) {
                    return true;
                }
            }

            employee.setPassword(encrypt(user.getPassword(), secret));
            MasterJobRole job = jobRole.findByJobName(user.getJobs());
            Set<MasterJobRole> roles = employee.getRoles();
            roles.add(job);
            employee.setRoles(roles);
            adminRepository.save(employee);
            return false;

        } else {
            MasterEmployee masterEmployee = new MasterEmployee();
            masterEmployee.setEmail(user.getEmail());
            masterEmployee.setEmpId(user.getId());
            masterEmployee.setLocation(user.getLocation());
            String encryptedPassword = encrypt(user.getPassword(), secret);
            masterEmployee.setPassword(encryptedPassword);
            masterEmployee.setEmpName(user.getName());
            Set<MasterJobRole> finalRoles = new HashSet<MasterJobRole>();
            finalRoles.add(jobRole.findByJobName(user.getJobs()));
            masterEmployee.setRoles(finalRoles);
            adminRepository.save(masterEmployee);
        }
        return false;
    }

    public boolean validateUser(Admin user) {

        List<MasterEmployee> allUsers = superService.fetchMasterEmployee();

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

    public boolean deleteUser(List<Admin> requestData) {

        return superService.deleteUserDB(requestData);
    }

    public List<MasterEmployee> searchUser() {

        return superService.searchUserDB();
    }

    @SuppressWarnings("unused")
    public List<Admin> searchManagerService(Admin requestData) {
        List<MasterEmployee> allEmployee = superService.fetchMasterEmployee();
        List<MasterEmployee> finalEmployees = new ArrayList<MasterEmployee>();
        List<Admin> responseData = new ArrayList<Admin>();
        for (MasterEmployee test : allEmployee) {
            if ((test.getLocation()).equals(requestData.getLocation())) {

                int check = test.getDeleteStatus();

                Set<MasterJobRole> roles = test.getRoles();
                for (MasterJobRole test2 : roles) {
                    if (test2.getJobRoleName().equals(requestData.getJobs())) {

                        Admin oneData = new Admin();
                        oneData.setEmail(test.getEmail());
                        oneData.setId(test.getEmpId());
                        oneData.setSno(test.getId());
                        oneData.setName(test.getEmpName());
                        oneData.setJob(requestData.getJobs());
                        oneData.setLocation(requestData.getLocation());
                        responseData.add(oneData);
                    }
                }
            }
        }
        return responseData;
    }

    public List<Admin> listManagersService() {
        List<MasterEmployee> employees = superService.fetchMasterEmployee();
        List<Admin> responseData = new ArrayList<Admin>();

        for (MasterEmployee test : employees) {
            if (test.getDeleteStatus() != 1) {
                for (MasterJobRole test2 : test.getRoles()) {
                    Admin oneData = new Admin();
                    oneData.setSno(test.getId());
                    oneData.setId(test.getEmpId());
                    oneData.setEmail(test.getEmail());
                    oneData.setLocation(test.getLocation());
                    oneData.setJob(test2.getJobRoleName());
                    oneData.setName(test.getEmpName());
                    responseData.add(oneData);
                }
            }
        }
        return responseData;
    }
}
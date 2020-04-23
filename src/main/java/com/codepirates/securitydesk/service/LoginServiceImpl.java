package com.codepirates.securitydesk.service;

import com.codepirates.securitydesk.entity.MasterEmployee;
import com.codepirates.securitydesk.entity.MasterJobRole;
import com.codepirates.securitydesk.repository.AdminRepositoryImpl;
import com.codepirates.securitydesk.repository.JobRoleImpl;
import com.codepirates.securitydesk.repository.SuperRepository;
import com.codepirates.securitydesk.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    SuperRepository adminrepo;

    @Autowired
    JobRoleImpl jobRoleImpl;

    @Autowired
    AdminRepositoryImpl adminRepositoryImpl;

    private static SecretKeySpec secretKey;
    private static byte[] key;
    final String secret = "ssshhhhhhhhhhh!!!!";

    public boolean validateLogin(UserModel userlogin) {
        MasterEmployee userLoginEntity = new MasterEmployee();
        userLoginEntity.setEmpId(userlogin.getEmployeeID());

        String encryptedString = encrypt(userlogin.getPassword(), secret);
        userlogin.setPassword(encryptedString);
        if (validateUser(userlogin)) {
            return true;
        }
        return false;
    }

    public boolean validateUser(UserModel userLogin) {
        List<MasterEmployee> allUsers = adminrepo.fetchMasterEmployee();
        int flag = 0;
        for (MasterEmployee test : allUsers) {
            if (test.getEmpId().equals(userLogin.getEmployeeID())) {
                flag = 1;
            }
        }

        if (flag != 1) {
            return false;
        }

        MasterEmployee employee = adminRepositoryImpl.findByEmpId(userLogin.getEmployeeID());

        if (employee.getEmpId().equals(userLogin.getEmployeeID())) {
            if (employee.getPassword().equals(userLogin.getPassword())) {
                if (employee.getRoles().size() != 0) {
                    Set<MasterJobRole> employeeRoles = employee.getRoles();
                    for (MasterJobRole test1 : employeeRoles) {
                        if (test1.getJobRoleName().equals(userLogin.getJob())) {
                            return true;
                        }
                    }
                }
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

    public List<String> loginRequest(UserModel userlogin) {

        String encryptedString = encrypt(userlogin.getPassword(), secret);
        userlogin.setPassword(encryptedString);

        List<MasterEmployee> allUsers = adminrepo.fetchMasterEmployee();
        int flag = 0;
        for (MasterEmployee test : allUsers) {
            if (test.getEmpId().equals(userlogin.getEmployeeID())) {
                flag = 1;
            }
        }

        if (flag != 1) {
            return null;
        }

        MasterEmployee employee = adminRepositoryImpl.findByEmpId(userlogin.getEmployeeID());

        if (employee.getPassword().equals(userlogin.getPassword())) {
            List<String> jobRoles = new ArrayList<String>();

            for (MasterJobRole test : employee.getRoles()) {
                jobRoles.add(test.getJobRoleName());
            }
            UserModel.setEmployeeName(employee.getEmpname());
            return jobRoles;
        }
        return null;
    }

    public String fetchusername() {

        return UserModel.getEmployeeName();
    }
}
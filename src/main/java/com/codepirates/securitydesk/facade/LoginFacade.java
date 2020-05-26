package com.codepirates.securitydesk.facade;

import com.codepirates.securitydesk.entity.MasterEmployee;
import com.codepirates.securitydesk.entity.MasterJobRole;
import com.codepirates.securitydesk.model.User;
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
public class LoginFacade {

    private final SuperService superService;
    private final JobRole jobRole;
    private final AdminRepository adminRepository;

    private static SecretKeySpec secretKey;
    private static byte[] key;
    final String secret = "ssshhhhhhhhhhh!!!!";

    public LoginFacade(SuperService superService, JobRole jobRole, AdminRepository adminRepository) {
        this.superService = superService;
        this.jobRole = jobRole;
        this.adminRepository = adminRepository;
    }

    public boolean validateLogin(User userlogin) {
        MasterEmployee userLoginEntity = new MasterEmployee();
        userLoginEntity.setEmpId(userlogin.getEmployeeID());

        String encryptedString = encrypt(userlogin.getPassword(), secret);
        userlogin.setPassword(encryptedString);
        if (validateUser(userlogin)) {
            return true;
        }
        return false;
    }

    public boolean validateUser(User userLogin) {
        List<MasterEmployee> allUsers = superService.fetchMasterEmployee();
        int flag = 0;
        for (MasterEmployee test : allUsers) {
            if (test.getEmpId().equals(userLogin.getEmployeeID())) {
                flag = 1;
            }
        }

        if (flag != 1) {
            return false;
        }

        MasterEmployee employee = adminRepository.findByEmpId(userLogin.getEmployeeID());

        if (employee.getEmpId().equals(userLogin.getEmployeeID())) {
            if (employee.getPassword().equals(userLogin.getPassword())) {
                userLogin.setEmployeeName (employee.getEmpName ());
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

    public List<String> loginRequest(User userlogin) {

        String encryptedString = encrypt(userlogin.getPassword(), secret);
        userlogin.setPassword(encryptedString);

        List<MasterEmployee> allUsers = superService.fetchMasterEmployee();
        int flag = 0;
        for (MasterEmployee test : allUsers) {
            if (test.getEmpId().equals(userlogin.getEmployeeID())) {
                flag = 1;
            }
        }

        if (flag != 1) {
            return null;
        }

        MasterEmployee employee = adminRepository.findByEmpId(userlogin.getEmployeeID());

        if (employee.getPassword().equals(userlogin.getPassword())) {
            List<String> jobRoles = new ArrayList<String>();

            for (MasterJobRole test : employee.getRoles()) {
                jobRoles.add(test.getJobRoleName());
            }
            userlogin.setEmployeeName(employee.getEmpName());
            return jobRoles;
        }
        return null;
    }
}

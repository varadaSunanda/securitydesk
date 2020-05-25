package com.codepirates.securitydesk.facade;

import com.codepirates.securitydesk.entity.MasterEmployee;
import com.codepirates.securitydesk.model.User;
import com.codepirates.securitydesk.repository.AdminRepository;
import com.codepirates.securitydesk.service.SuperService;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Component
public class PasswordFacade {

    private final AdminRepository adminRepository;
    private final SuperService superService;

    private static SecretKeySpec secretKey;
    private static byte[] key;
    final String secret = "ssshhhhhhhhhhh!!!!";

    public PasswordFacade(AdminRepository adminRepository, SuperService superService) {
        this.adminRepository = adminRepository;
        this.superService = superService;
    }

    public boolean validatePass(User userpass) {

        String encryptedString = encrypt(userpass.getPassword(), secret);
        userpass.setPassword(encryptedString);

        return Passvalidator(userpass);
    }

    public boolean Passvalidator(User userLogin) {
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

        if (employee.getPassword().equals(userLogin.getPassword())) {

            String encryptedString = encrypt(userLogin.getNewPassword(), secret);
            userLogin.setPassword(encryptedString);

            employee.setPassword(encryptedString);
            adminRepository.save(employee);
            return true;
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
}
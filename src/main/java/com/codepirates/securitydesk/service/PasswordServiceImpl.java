package com.codepirates.securitydesk.service;

import com.codepirates.securitydesk.entity.MasterEmployee;
import com.codepirates.securitydesk.repository.AdminRepositoryImpl;
import com.codepirates.securitydesk.repository.SuperRepository;
import com.codepirates.securitydesk.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class PasswordServiceImpl implements PasswordService {

    @Autowired
    AdminRepositoryImpl adminRepositoryImpl;

    @Autowired
    SuperRepository adminrepo;

    private static SecretKeySpec secretKey;
    private static byte[] key;
    final String secret = "ssshhhhhhhhhhh!!!!";

    public boolean validatePass(UserModel userpass) {

        String encryptedString = encrypt(userpass.getPassword(), secret);
        userpass.setPassword(encryptedString);

        return Passvalidator(userpass);
    }

    public boolean Passvalidator(UserModel userLogin) {
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

        if (employee.getPassword().equals(userLogin.getPassword())) {

            String encryptedString = encrypt(userLogin.getNewpassword(), secret);
            userLogin.setPassword(encryptedString);

            employee.setPassword(encryptedString);
            adminRepositoryImpl.save(employee);
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
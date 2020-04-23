package com.codepirates.securitydesk.service;

import com.codepirates.securitydesk.entity.MasterEmployee;
import com.codepirates.securitydesk.repository.AdminRepositoryImpl;
import com.codepirates.securitydesk.repository.SuperRepository;
import com.codepirates.securitydesk.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Properties;

@Service
public class SendMailServiceImpl implements SendMailService {

    String m_from = "from@test.com";
    String m_to = "G1-G6@test.com";

    @Autowired
    SuperRepository superRepository;

    @Autowired
    AdminRepositoryImpl adminRepositoryImpl;

    private static SecretKeySpec secretKey;
    private static byte[] key;
    final String secret = "ssshhhhhhhhhhh!!!!";

    public boolean forgetPass(UserModel forgetpass) {

        for (MasterEmployee test : superRepository.fetchMasterEmployee()) {
            if (test.getEmpId().equals(forgetpass.getEmployeeID())) {

                String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

                StringBuilder sb = new StringBuilder(6);

                for (int i = 0; i < 6; i++) {

                    int index = (int) (AlphaNumericString.length() * Math.random());

                    sb.append(AlphaNumericString.charAt(index));
                }
                String newPassword = sb.toString();

                String m_from = "from@test.com";
                String m_to = "G1-G6@test.com";
                String Empid = test.getEmpId();
                String Pass = "New Password: " + newPassword;

                MasterEmployee employee = adminRepositoryImpl.findByEmpId(test.getEmpId());
                employee.setPassword(encrypt(newPassword, secret));
                adminRepositoryImpl.save(employee);

                return sendMail(m_from, m_to, Empid, Pass);
            }
        }
        return false;
    }

    public boolean sendMail(String m_from, String m_to, String m_subject, String m_body) {

        try {
            Session m_Session;
            Message m_simpleMessage;
            InternetAddress m_fromAddress;
            InternetAddress m_toAddress;
            Properties m_properties;

            m_properties = new Properties();
            m_properties.put("mail.smtp.host", "192.168.29.2");
            m_properties.put("mail.smtp.auth", "true");
            m_properties.put("mail.smtp.starttls.enable", "true");
            m_properties.put("mail.smtp.port", "25");

            m_properties.setProperty("mail.smtp.socketFactory.fallback", "false");

            m_properties.put("mail.debug", "true");

            m_properties.put("mail.transport.protocol", "smtp");

            m_Session = Session.getDefaultInstance(m_properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("from@test.com", "from");
                }
            });

            m_simpleMessage = new MimeMessage(m_Session);
            m_fromAddress = new InternetAddress(m_from);
            m_toAddress = new InternetAddress(m_to);

            m_simpleMessage.setFrom(m_fromAddress);
            m_simpleMessage.setRecipient(RecipientType.TO, m_toAddress);
            m_simpleMessage.setSubject(m_subject);

            m_simpleMessage.setContent(m_body, "text/html");

            Transport.send(m_simpleMessage);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return true;
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
package com.codepirates.securitydesk.service;

import com.codepirates.securitydesk.model.UserModel;

public interface SendMailService {

    boolean forgetPass(UserModel forgetpass);

    boolean sendMail(String m_from, String m_to, String m_subject, String m_body);

}

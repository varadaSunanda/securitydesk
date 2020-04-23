package com.codepirates.securitydesk.service;

import com.codepirates.securitydesk.model.UserModel;

public interface PasswordService {

    boolean validatePass(UserModel userpass);

    boolean Passvalidator(UserModel userLogin);

}
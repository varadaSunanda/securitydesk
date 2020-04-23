package com.codepirates.securitydesk.service;

import com.codepirates.securitydesk.model.UserModel;

import java.util.List;

public interface LoginService {

    boolean validateLogin(UserModel userlogin);

    boolean validateUser(UserModel userLogin);

    List<String> loginRequest(UserModel userlogin);

    String fetchusername();
}
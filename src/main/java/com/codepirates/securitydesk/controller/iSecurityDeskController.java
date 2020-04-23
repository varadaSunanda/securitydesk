package com.codepirates.securitydesk.controller;

import com.codepirates.securitydesk.entity.ExcelEmployeeList;
import com.codepirates.securitydesk.entity.MasterEmployee;
import com.codepirates.securitydesk.service.AdminService;
import com.codepirates.securitydesk.service.LoginService;
import com.codepirates.securitydesk.service.PasswordService;
import com.codepirates.securitydesk.service.SendMailService;
import com.codepirates.securitydesk.model.AdminModel;
import com.codepirates.securitydesk.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class iSecurityDeskController {

    @Autowired
    AdminService adminService;
    @Autowired
    LoginService loginService;
    @Autowired
    SendMailService sendMailService;
    @Autowired
    PasswordService passwordService;

    @RequestMapping(value = "/user/loginRequest", method = RequestMethod.POST)
    public List<String> loginRequest(@RequestBody UserModel logininfo) {

        return loginService.loginRequest(logininfo);
    }

    @RequestMapping(value = "/user/fetchUsername", method = RequestMethod.POST)
    @ResponseBody
    public String fetchUsername() {

        return loginService.fetchusername();
    }

    @RequestMapping(value = "/user/validateLogin", method = RequestMethod.POST)
    @ResponseBody
    public boolean validateLogin(@RequestBody UserModel userlogin) {

        boolean status = false;
        status = loginService.validateLogin(userlogin);
        return status;
    }

    @RequestMapping(value = "/user/forgetPassword", method = RequestMethod.POST)
    public boolean forgetPass(@RequestBody UserModel forgetpass) {

        boolean status = false;
        status = sendMailService.forgetPass(forgetpass);
        return status;
    }

    @RequestMapping(value = "/user/validatePassword", method = RequestMethod.POST)
    public boolean validatePass(@RequestBody UserModel userpass) {

        boolean status = false;
        status = passwordService.validatePass(userpass);
        return status;
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public boolean showUser(@RequestBody AdminModel user) {

        return adminService.saveUser(user);
    }

    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    public boolean delete(@RequestBody List<AdminModel> requestData) {

        return adminService.deleteUser(requestData);
    }

    @RequestMapping(value = "/adminSearch", method = RequestMethod.GET)
    public List<MasterEmployee> search() {

        return adminService.searchUser();
    }

    @RequestMapping(value = "/user/listManagers", method = RequestMethod.POST)
    public List<AdminModel> listManagersController(@RequestBody int a) {

        return adminService.listManagersService();
    }

    @RequestMapping(value = "/user/search", method = RequestMethod.POST)
    public List<AdminModel> search(@RequestBody AdminModel requestData) {

        return adminService.searchManagerService(requestData);
    }

    @RequestMapping(value = "/allUsers", method = RequestMethod.POST)
    public List<ExcelEmployeeList> listAllUsersExcel(@RequestBody int a) {

        return adminService.searchAllUserExcel();
    }
}
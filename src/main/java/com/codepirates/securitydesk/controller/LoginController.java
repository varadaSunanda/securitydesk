package com.codepirates.securitydesk.controller;

import com.codepirates.securitydesk.entity.MasterEmployee;
import com.codepirates.securitydesk.facade.AdminFacade;
import com.codepirates.securitydesk.facade.LoginFacade;
import com.codepirates.securitydesk.facade.PasswordFacade;
import com.codepirates.securitydesk.facade.SendMailFacade;
import com.codepirates.securitydesk.model.Admin;
import com.codepirates.securitydesk.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sjangra on 5/11/20.
 */

@RestController
@RequestMapping("/user")
public class LoginController {

    private final AdminFacade adminService;
    private final LoginFacade loginService;
    private final SendMailFacade sendMailService;
    private final PasswordFacade passwordService;

    public LoginController(AdminFacade adminFacade, LoginFacade loginFacade,
                           SendMailFacade sendMailFacade, PasswordFacade passwordFacade) {
        this.adminService = adminFacade;
        this.loginService = loginFacade;
        this.sendMailService = sendMailFacade;
        this.passwordService = passwordFacade;
    }

    @RequestMapping(value = "/loginRequest", method = RequestMethod.POST)
    public List<String> loginRequest(@RequestBody User user) {

        return loginService.loginRequest(user);
    }


    @RequestMapping(value = "/validateLogin", method = RequestMethod.POST)
    @ResponseBody
    public User validateLogin(@RequestBody User user) {

        boolean status = false;
        status = loginService.validateLogin(user);
        return user;
    }

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public boolean forgetPass(@RequestBody User user) {

        boolean status = false;
        status = sendMailService.forgetPass(user);
        return status;
    }

    @RequestMapping(value = "/validatePassword", method = RequestMethod.POST)
    public boolean validatePass(@RequestBody User user) {

        boolean status = false;
        status = passwordService.validatePass(user);
        return status;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public boolean showUser(@RequestBody Admin user) {

        return adminService.saveUser(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public boolean delete(@RequestBody List<Admin> requestData) {

        return adminService.deleteUser(requestData);
    }

    @RequestMapping(value = "/adminSearch", method = RequestMethod.GET)
    public List<MasterEmployee> search() {

        return adminService.searchUser();
    }

    @RequestMapping(value = "/listManagers", method = RequestMethod.POST)
    public List<Admin> listManagersController(@RequestBody int extra) {

        return adminService.listManagersService();
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Admin> search(@RequestBody Admin requestData) {

        return adminService.searchManagerService(requestData);
    }
}

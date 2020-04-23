package com.codepirates.securitydesk.service;

import com.codepirates.securitydesk.entity.ExcelEmployeeList;
import com.codepirates.securitydesk.entity.MasterEmployee;
import com.codepirates.securitydesk.model.AdminModel;

import java.util.List;

public interface AdminService {

    boolean saveUser(AdminModel user);

    boolean validateUser(AdminModel user);

    boolean deleteUser(List<AdminModel> requestData);

    List<MasterEmployee> searchUser();

    List<AdminModel> searchManagerService(AdminModel requestData);

    List<ExcelEmployeeList> searchAllUserExcel();

    List<AdminModel> listManagersService();
}
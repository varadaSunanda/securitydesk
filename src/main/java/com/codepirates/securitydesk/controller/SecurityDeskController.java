package com.codepirates.securitydesk.controller;

import com.codepirates.securitydesk.model.LateNightConeyanceModel;
import com.codepirates.securitydesk.repository.EmployeeDAL;
import com.codepirates.securitydesk.repository.EmployeeRepository;
import com.codepirates.securitydesk.util.CommonFunctions;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SecurityDeskController {

    private final EmployeeRepository employeeRepository;
    private final EmployeeDAL employeeDAL;
    private final CommonFunctions commonFunctions;

    public SecurityDeskController(EmployeeRepository employeeRepository, EmployeeDAL employeeDAL, CommonFunctions commonFunctions) {
        this.employeeRepository = employeeRepository;
        this.employeeDAL = employeeDAL;
        this.commonFunctions = commonFunctions;
    }

    @RequestMapping(value = "/createLNCEntry", method = RequestMethod.POST)
    public List<LateNightConeyanceModel> addNewLNCEntry(@RequestBody LateNightConeyanceModel lateNightConeyanceModel) {
        lateNightConeyanceModel.setCheckInTime(commonFunctions.currentDateAndTime());
        employeeDAL.addNewLateNightEntry(lateNightConeyanceModel);
        return getLNCEntry();
    }

    @RequestMapping(value = "/getLNCEntry", method = RequestMethod.GET)
    public List<LateNightConeyanceModel> getLNCEntry() {
        return employeeRepository.findAllByCheckOutTimeIsNotNull();
    }

    @RequestMapping(value = "/updateLNCEntry/{employeeId}", method = RequestMethod.GET)
    public List<LateNightConeyanceModel> getLNCEntry(@PathVariable String employeeId) {
        employeeDAL.updateLateNightEntry(employeeId);
        //to return the list of rest of employees
        return getLNCEntry();
    }
}

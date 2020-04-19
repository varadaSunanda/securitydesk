package com.codepirates.securitydesk.Controller;


import com.codepirates.securitydesk.Model.LateNightConeyanceModel;
import com.codepirates.securitydesk.Repository.EmployeeDAL;
import com.codepirates.securitydesk.Repository.EmployeeRepository;
import com.codepirates.securitydesk.Util.CommonFuntions;
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
    private final CommonFuntions commonFuntions;

    public SecurityDeskController(EmployeeRepository employeeRepository, EmployeeDAL employeeDAL, CommonFuntions commonFuntions) {
        this.employeeRepository = employeeRepository;
        this.employeeDAL = employeeDAL;
        this.commonFuntions = commonFuntions;
    }

    @RequestMapping(value = "/createLNCEntry", method = RequestMethod.POST)
    public LateNightConeyanceModel addNewLNCEntry(@RequestBody LateNightConeyanceModel lateNightConeyanceModel) {
        lateNightConeyanceModel.setCheckInTime(commonFuntions.currentDateAndTime ());
        return employeeDAL.addNewLateNightEntry(lateNightConeyanceModel);
    }

    @RequestMapping(value = "/getLNCEntry", method = RequestMethod.GET)
    public List<LateNightConeyanceModel> getLNCEntry() {
        return employeeRepository.findAllByCheckOutTimeIsNotNull ();
    }

    @RequestMapping(value = "/updateLNCEntry/{employeeId}", method = RequestMethod.GET)
    public List<LateNightConeyanceModel> getLNCEntry(@PathVariable String employeeId) {
        employeeDAL.updateLateNightEntry (employeeId);
        //to return the list of rest of employees
        return getLNCEntry ();
    }
}

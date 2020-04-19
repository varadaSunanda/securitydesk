package com.codepirates.securitydesk.Repository;

import com.codepirates.securitydesk.Model.Employee;
import com.codepirates.securitydesk.Model.LateNightConeyanceModel;
import com.mongodb.client.result.UpdateResult;

import java.util.List;

public interface EmployeeDAL {

    List<LateNightConeyanceModel> getAllEmployee();

    Employee getEmployeeById(String employeeId);

    UpdateResult updateLateNightEntry(String employeeId);

    LateNightConeyanceModel addNewLateNightEntry(LateNightConeyanceModel lateNightConeyanceModel);
}

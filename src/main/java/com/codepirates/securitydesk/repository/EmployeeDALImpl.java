package com.codepirates.securitydesk.repository;

import com.codepirates.securitydesk.model.Employee;
import com.codepirates.securitydesk.model.LateNightConeyanceModel;
import com.codepirates.securitydesk.util.CommonFunctions;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDALImpl implements EmployeeDAL {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private CommonFunctions commonFunctions;


    @Override
    public List<LateNightConeyanceModel> getAllEmployee() {
        return mongoTemplate.findAll(LateNightConeyanceModel.class);
    }

    @Override
    public Employee getEmployeeById(String employeeId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("employeeId").is(employeeId));
        return mongoTemplate.findOne(query, Employee.class);
    }

    @Override
    public UpdateResult updateLateNightEntry(String employeeId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("employeeId").is(employeeId));
        query.addCriteria(Criteria.where("checkoutTime").is(null));
        return mongoTemplate.updateMulti(query, Update.update("checkOutTime", commonFunctions.currentDateAndTime()), LateNightConeyanceModel.class);
    }

    @Override
    public LateNightConeyanceModel addNewLateNightEntry(LateNightConeyanceModel lateNightConeyanceModel) {
        return mongoTemplate.save(lateNightConeyanceModel);
    }


    @Override
    public boolean checkIfEmployeeAlreadyExists(String employeeId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("employeeId").is(employeeId)).addCriteria(Criteria.where("checkOutTime").is(null));
        return mongoTemplate.count(query, LateNightConeyanceModel.class) > 0 ? true : false;
    }}

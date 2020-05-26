package com.codepirates.securitydesk.repository;

import com.codepirates.securitydesk.entity.MasterEmployee;
import com.codepirates.securitydesk.validators.InputValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminRepositoryImpl implements AdminRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public MasterEmployee findByEmpId(String empId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("EmpId").is(empId));
        return mongoTemplate.findOne(query, MasterEmployee.class);
    }

    @Override
    public MasterEmployee findByIdOne(int sno) {
        final Query query = constructQueryForFindByIdOne(sno);

        return mongoTemplate.findOne(query, MasterEmployee.class);
    }

    @Override
    public MasterEmployee save(MasterEmployee employee) {
        return mongoTemplate.save(employee);
    }

    @Override
    public List<MasterEmployee> findAll() {
        return mongoTemplate.findAll(MasterEmployee.class);
    }

    private Query constructQueryForFindOne(String empId) {
        final Criteria criteria = new Criteria("empId").is(InputValidators.validateMongoQueryParameter(empId));
        return Query.query(criteria);
    }

    private Query constructQueryForFindByIdOne(int sno) {
        final Criteria criteria = new Criteria("sno").in(sno);
        return Query.query(criteria);
    }
}

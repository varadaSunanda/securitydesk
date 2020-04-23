package com.codepirates.securitydesk.repository;

import com.codepirates.securitydesk.entity.MasterEmployee;
import com.codepirates.securitydesk.validators.InputValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public abstract class AdminRepositoryImpl implements AdminRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public MasterEmployee findByEmpId(String empId) {
        final Query query = constructQueryForFindOne(empId);

        return mongoTemplate.findOne(query, MasterEmployee.class);
    }

    @Override
    public MasterEmployee findByIdOne(int sno) {
        final Query query = constructQueryForFindByIdOne(sno);

        return mongoTemplate.findOne(query, MasterEmployee.class);
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
package com.codepirates.securitydesk.repository;

import com.codepirates.securitydesk.entity.MasterJobRole;
import com.codepirates.securitydesk.validators.InputValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class JobRoleImpl implements JobRole {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public MasterJobRole findByJobName(String jobName) {
        final Query query = constructQueryForFindByJobName(jobName);

        return mongoTemplate.findOne(query, MasterJobRole.class);
    }

    private Query constructQueryForFindByJobName(String jobName) {
        final Criteria criteria = new Criteria("jobName").is(InputValidators.validateMongoQueryParameter(jobName));
        return Query.query(criteria);
    }
}
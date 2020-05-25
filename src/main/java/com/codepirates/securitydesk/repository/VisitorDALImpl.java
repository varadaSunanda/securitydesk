package com.codepirates.securitydesk.repository;

import com.codepirates.securitydesk.model.Visitor;
import com.codepirates.securitydesk.util.CommonFunctions;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class VisitorDALImpl implements VisitorDAL {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    CommonFunctions commonFunctions;

    @Override
    public Visitor addVisitor(Visitor visitor){
        return mongoTemplate.save(visitor);
    }

    @Override
    public UpdateResult updateVisitor(String tagId){
        Query query = new Query();
        query.addCriteria (Criteria.where ("tagId").is (tagId));
        query.addCriteria(Criteria.where("checkoutTime").is(null));
        return mongoTemplate.updateMulti(query, Update.update("checkOutTime", commonFunctions.currentDateAndTime()), Visitor.class);
    }
}

package com.codepirates.securitydesk.repository;

import com.codepirates.securitydesk.model.Baggage;
import com.codepirates.securitydesk.model.Token;
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
public class BaggageRepositoryImpl implements BaggageRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CommonFunctions commonFunctions;

    @Override
    public Baggage addNewBaggage(Baggage baggage) {
        return mongoTemplate.save(baggage);
    }

    @Override
    public UpdateResult updateBaggage(String employeeId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("employeeId").is(employeeId));
        query.addCriteria(Criteria.where("checkoutTime").is(null));
        return mongoTemplate.updateFirst(query, Update.update("checkoutTime", commonFunctions.currentDateAndTime()), Baggage.class);
    }

    @Override
    public List<Baggage> getAllBaggages() {
        return mongoTemplate.findAll(Baggage.class);
    }

    @Override
    public List<Baggage> getAllCheckinedBaggages() {
        Query query = new Query();
        query.addCriteria(Criteria.where("checkoutTime").is(null));
        return mongoTemplate.find(query, Baggage.class);
    }

    @Override
    public List<Token> getAllUsableTokens() {
        Query query = new Query();
        query.addCriteria(Criteria.where("checkoutTime").is(null));
        List<String> tokenIds = mongoTemplate.findDistinct(query, "token", Baggage.class, String.class);
        Query tokenQuery = new Query();
        tokenQuery.addCriteria(Criteria.where("tokenId").nin(tokenIds));
        return mongoTemplate.find(tokenQuery, Token.class);
    }
}

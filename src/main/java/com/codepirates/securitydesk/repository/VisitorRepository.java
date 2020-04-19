package com.codepirates.securitydesk.repository;

import com.codepirates.securitydesk.model.Visitor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitorRepository extends MongoRepository<Visitor, String> {

    @Query("{'checkOutTime' : null}")
    List<Visitor> findAllByCheckOutTimeIsNotNull();

}

package com.codepirates.securitydesk.repository;

import com.codepirates.securitydesk.model.Visitor;
import com.mongodb.client.result.UpdateResult;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface VisitorDAL {

    Visitor addVisitor(Visitor visitor);
    UpdateResult updateVisitor(String tagId);
}

package com.codepirates.securitydesk.repository;

import com.codepirates.securitydesk.entity.ExcelEmployeeList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelListRepository extends MongoRepository<ExcelEmployeeList, String> {

}
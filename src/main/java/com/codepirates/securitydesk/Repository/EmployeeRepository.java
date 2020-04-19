package com.codepirates.securitydesk.Repository;

import com.codepirates.securitydesk.Model.Employee;
import com.codepirates.securitydesk.Model.LateNightConeyanceModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    @Query("{'checkOutTime' : null}")
    List<LateNightConeyanceModel> findAllByCheckOutTimeIsNotNull();

}

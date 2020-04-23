package com.codepirates.securitydesk.repository;

        import com.codepirates.securitydesk.entity.MasterEmployee;
        import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<MasterEmployee, String> {

    MasterEmployee findByEmpId(String empId);

    MasterEmployee findByIdOne(int sno);
}
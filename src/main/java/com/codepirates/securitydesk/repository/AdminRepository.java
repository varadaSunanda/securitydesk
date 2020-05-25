package com.codepirates.securitydesk.repository;

import com.codepirates.securitydesk.entity.MasterEmployee;

import java.util.List;

public interface AdminRepository {

    MasterEmployee findByEmpId(String empId);

    MasterEmployee findByIdOne(int sno);

    MasterEmployee save(MasterEmployee employee);

    List<MasterEmployee> findAll();
}
package com.codepirates.securitydesk.service;

import com.codepirates.securitydesk.entity.MasterEmployee;
import com.codepirates.securitydesk.entity.MasterJobRole;
import com.codepirates.securitydesk.model.Admin;
import com.codepirates.securitydesk.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SuperService {

    private final AdminRepository adminRepository;

    public SuperService(@Qualifier("adminRepositoryImpl") AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @SuppressWarnings("unused")
    public boolean saveUser(MasterEmployee masterEmployee) {

        adminRepository.save(masterEmployee);
        return true;
    }

    @SuppressWarnings("unused")
    public boolean deleteUserDB(List<Admin> requestDataAll) {

        for (Admin requestData : requestDataAll) {
            String empId = requestData.getId();
            MasterEmployee user = adminRepository.findByIdOne(requestData.getSno());
            Set<MasterJobRole> updatedJobRoles = new HashSet<MasterJobRole>();
            for (MasterJobRole test : user.getRoles()) {
                if (test.getJobRoleName().equals(requestData.getJobs())) {

                } else {
                    updatedJobRoles.add(test);
                }
            }

            user.setRoles(updatedJobRoles);

            if (updatedJobRoles.size() != 0) {
                adminRepository.save(user);
            } else {
                user.setDeleteStatus(1);
                adminRepository.save(user);
            }
        }
        return true;
    }

    public List<MasterEmployee> searchUserDB() {

        List<MasterEmployee> categoryDetails = new ArrayList<MasterEmployee>();
        List<MasterEmployee> finalCategoryDetails = new ArrayList<MasterEmployee>();
        adminRepository.findAll().forEach(categoryDetails::add);

        for (MasterEmployee test : categoryDetails) {
            if (test.getDeleteStatus() != 1) {
                finalCategoryDetails.add(test);
            }
        }
        return finalCategoryDetails;
    }

    public List<MasterEmployee> fetchMasterEmployee() {

        List<MasterEmployee> list = new ArrayList<MasterEmployee>();
        adminRepository.findAll().forEach(list::add);

        return list;
    }
}
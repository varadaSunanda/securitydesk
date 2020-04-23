package com.codepirates.securitydesk.repository;

import com.codepirates.securitydesk.entity.ExcelEmployeeList;
import com.codepirates.securitydesk.entity.MasterEmployee;
import com.codepirates.securitydesk.entity.MasterJobRole;
import com.codepirates.securitydesk.model.AdminModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class SuperRepository {

    @Qualifier("adminRepository")
    @Autowired
    AdminRepository userrepository;

    @Autowired
    ExcelListRepository excelListRepository;

    public boolean saveUser(MasterEmployee masterEmployee) {

        userrepository.save(masterEmployee);
        return true;
    }

    @SuppressWarnings("unused")
    public boolean deleteUserDB(List<AdminModel> requestDataAll) {

        for (AdminModel requestData : requestDataAll) {
            String empId = requestData.getId();
            MasterEmployee user = userrepository.findByIdOne(requestData.getSno());
            Set<MasterJobRole> updatedJobRoles = new HashSet<MasterJobRole>();
            for (MasterJobRole test : user.getRoles()) {
                if (test.getJobRoleName().equals(requestData.getJobs())) {

                } else {
                    updatedJobRoles.add(test);
                }
            }

            user.setRoles(updatedJobRoles);

            if (updatedJobRoles.size() != 0) {
                userrepository.save(user);
            } else {
                user.setDeleteStatus(1);
                userrepository.save(user);
            }
        }
        return true;
    }

    public List<MasterEmployee> searchUserDB() {

        List<MasterEmployee> categoryDetails = new ArrayList<MasterEmployee>();
        List<MasterEmployee> finalCategoryDetails = new ArrayList<MasterEmployee>();
        userrepository.findAll().forEach(categoryDetails::add);

        for (MasterEmployee test : categoryDetails) {
            if (test.getDeleteStatus() != 1) {
                finalCategoryDetails.add(test);
            }
        }
        return finalCategoryDetails;
    }

    public List<MasterEmployee> fetchMasterEmployee() {

        List<MasterEmployee> list = new ArrayList<MasterEmployee>();
        userrepository.findAll().forEach(list::add);

        return list;
    }

    public List<ExcelEmployeeList> listAllUsersExcel() {
        List<ExcelEmployeeList> excelList = new ArrayList<ExcelEmployeeList>();
        excelListRepository.findAll().forEach(excelList::add);
        return excelList;
    }
}
package com.codepirates.securitydesk.repository;

import com.codepirates.securitydesk.entity.MasterJobRole;

public interface JobRole {

    MasterJobRole findByJobName(String jobName);
}
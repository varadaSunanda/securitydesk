package com.codepirates.securitydesk.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class MasterJobRole {

    @Expose
    @SerializedName("Id")
    @Id
    private int Id;

    @Expose
    @SerializedName("JobRoleName")
    @Field("JobRoleName")
    private String JobRoleName;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getJobRoleName() {
        return JobRoleName;
    }

    public void setJobRoleName(String jobRoleName) {
        JobRoleName = jobRoleName;
    }
}
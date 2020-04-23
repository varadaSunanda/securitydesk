package com.codepirates.securitydesk.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;

public class MasterJobRole {

    @Expose
    @SerializedName("id")
    @Id
    @GenericGenerator(name = "Id", strategy = "increment")
    @GeneratedValue(generator = "Id", strategy = GenerationType.AUTO)
    private int id;

    @Expose
    @SerializedName("jobRoleName")
    @Field("jobRoleName")
    private String jobRoleName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobRoleName() {
        return jobRoleName;
    }

    public void setJobRoleName(String jobRoleName) {
        this.jobRoleName = jobRoleName;
    }
}
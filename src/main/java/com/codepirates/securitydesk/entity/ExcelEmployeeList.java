package com.codepirates.securitydesk.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class ExcelEmployeeList {

    @Expose
    @SerializedName("EMPLOYEE_ID")
    @Id
    private String EMPLOYEE_ID;

    @Expose
    @SerializedName("EMPLOYEE_NAME")
    @Field("EMPLOYEE_NAME")
    private String EMPLOYEE_NAME;

    @Expose
    @SerializedName("DESIGNATION")
    @Field("DESIGNATION")
    private String DESIGNATION;

    @Expose
    @SerializedName("JOB_LEVEL")
    @Field("JOB_LEVEL")
    private String JOB_LEVEL;

    @Expose
    @SerializedName("DEPARTMENT")
    @Field("DEPARTMENT")
    private String DEPARTMENT;

    @Expose
    @SerializedName("PROJECT")
    @Field("PROJECT")
    private String PROJECT;

    @Expose
    @SerializedName("CITY")
    @Field("CITY")
    private String CITY;

    @Expose
    @SerializedName("EMAIL")
    @Field("EMAIL")
    private String EMAIL;

    public String getEMPLOYEE_ID() {
        return EMPLOYEE_ID;
    }

    public void setEMPLOYEE_ID(String EMPLOYEE_ID) {
        this.EMPLOYEE_ID = EMPLOYEE_ID;
    }

    public String getEMPLOYEE_NAME() {
        return EMPLOYEE_NAME;
    }

    public void setEMPLOYEE_NAME(String EMPLOYEE_NAME) {
        this.EMPLOYEE_NAME = EMPLOYEE_NAME;
    }

    public String getDESIGNATION() {
        return DESIGNATION;
    }

    public void setDESIGNATION(String DESIGNATION) {
        this.DESIGNATION = DESIGNATION;
    }

    public String getJOB_LEVEL() {
        return JOB_LEVEL;
    }

    public void setJOB_LEVEL(String JOB_LEVEL) {
        this.JOB_LEVEL = JOB_LEVEL;
    }

    public String getDEPARTMENT() {
        return DEPARTMENT;
    }

    public void setDEPARTMENT(String DEPARTMENT) {
        this.DEPARTMENT = DEPARTMENT;
    }

    public String getPROJECT() {
        return PROJECT;
    }

    public void setPROJECT(String PROJECT) {
        this.PROJECT = PROJECT;
    }

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }
}
package com.codepirates.securitydesk.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

    private String employeeID;
    private String password;
    private String newPassword;
    private String job;
    private String employeeName;

    public  String getEmployeeName() {
        return employeeName;
    }

    public  void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

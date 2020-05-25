package com.codepirates.securitydesk.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

public class MasterEmployee {

    @Expose
    @SerializedName("Id")
    @Id
    private int Id;

    @Expose
    @SerializedName("EmpId")
    @Field("EmpId")
    private String EmpId;

    @Expose
    @SerializedName("Password")
    @Field("Password")
    private String Password;

    @Expose
    @SerializedName("Email")
    @Field("Email")
    private String Email;

    @Expose
    @SerializedName("Location")
    @Field("Location")
    private String Location;

    @Expose
    @SerializedName("Roles")
    @Field("Roles")
    private Set<MasterJobRole> Roles;

    @Expose
    @SerializedName("EmpName")
    @Field("EmpName")
    private String EmpName;

    @Expose
    @SerializedName("DeleteStatus")
    @Field("DeleteStatus")
    private int DeleteStatus;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getEmpId() {
        return EmpId;
    }

    public void setEmpId(String empId) {
        EmpId = empId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public Set<MasterJobRole> getRoles() {
        return Roles;
    }

    public void setRoles(Set<MasterJobRole> roles) {
        this.Roles = roles;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }

    public int getDeleteStatus() {
        return DeleteStatus;
    }

    public void setDeleteStatus(int deleteStatus) {
        DeleteStatus = deleteStatus;
    }
}
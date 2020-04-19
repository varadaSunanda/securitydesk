package com.codepirates.securitydesk.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class LateNightConeyanceModel extends Employee {

    private String checkInTime;
    private String checkOutTime;

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
}

package com.codepirates.securitydesk.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Baggage extends Employee {

    private String token;
    private String checkinTime;
    private String checkoutTime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(String checkinTime) {
        this.checkinTime = checkinTime;
    }

    public String getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(String checkoutTime) {
        this.checkoutTime = checkoutTime;
    }
}

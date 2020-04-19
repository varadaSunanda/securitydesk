package com.codepirates.securitydesk.util;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CommonFunctions {

    public String currentDateAndTime() {
        DateFormat df = new SimpleDateFormat ("dd/MM/yy HH:mm:ss");
        Date dateobj = new Date();
        return df.format (dateobj);
    }
}

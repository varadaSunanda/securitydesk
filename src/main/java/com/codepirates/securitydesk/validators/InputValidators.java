package com.codepirates.securitydesk.validators;

public class InputValidators {

    private InputValidators() {
        //NOOP
    }

    public static String validateMongoQueryParameter(String parameter) {
        return parameter.replace("$", "");
    }
}
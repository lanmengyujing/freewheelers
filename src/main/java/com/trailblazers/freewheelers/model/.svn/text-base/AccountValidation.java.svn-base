package com.trailblazers.freewheelers.model;

import java.util.HashMap;
import java.util.regex.Pattern;

public class AccountValidation {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static HashMap verifyInputs(Account account) {
        HashMap errors = new HashMap();

        if (account.getAcceptedTerms() == null || account.getAcceptedTerms().isEmpty()) {
            errors.put("acceptedTerms", "Must accept the terms and conditions!");
        }

        if (!isValidEmail(account.getEmailAddress())) {
            errors.put("email", "Must enter a valid email!");
        }

        if (account.getPassword().isEmpty()) {
            errors.put("password", "Must enter a password!");
        }

        if (account.getAccount_name().isEmpty()) {
            errors.put("name", "Must enter a name!");
        }

        errors.putAll(verifyAddress(account.getAddress()));

        return errors;
    }

    private static HashMap verifyAddress(Address accountAddress) {

        HashMap errors = new HashMap();
        if (accountAddress.getCountry().isEmpty()) {
            errors.put("country", "Must enter a country!");
        }

        if (accountAddress.getCity() != null) {
            if (!accountAddress.getCity().matches("[a-zA-Z\\-\\s]*")) {
                errors.put("city", "Must enter only letters!");
            }
        }

        if (accountAddress.getState() != null) {
            if (!accountAddress.getState().matches("[a-zA-Z\\-\\s]*")) {
                errors.put("state", "Must enter only letters!");
            }
        }

        return errors;
    }

    private static boolean isValidEmail(String emailAddress) {
        Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
        return emailPattern.matcher(emailAddress).matches();
    }
}
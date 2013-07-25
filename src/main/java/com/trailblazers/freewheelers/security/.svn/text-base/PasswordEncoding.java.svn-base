package com.trailblazers.freewheelers.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordEncoding {

    private BCryptPasswordEncoder passwordEncoder;

    public PasswordEncoding(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String encode(String password) {
        if (password == null) return null;

        if (password.length() == 0)
            return "";
        else
            return passwordEncoder.encode(password);

    }
}

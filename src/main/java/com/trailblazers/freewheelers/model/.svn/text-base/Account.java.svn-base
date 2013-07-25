package com.trailblazers.freewheelers.model;

import com.google.common.base.Objects;

import static com.google.common.base.Objects.equal;

public class Account {

    private Long account_id;
    private String account_name;
    private String password;
    private boolean enabled;
    private String emailAddress;
    private String phoneNumber;
    private Address address;
    private String acceptedTerms;

    public Account() {
        this.account_id = 0L;
        this.address = new Address();
        this.enabled = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        return equal(enabled, account.enabled) &&
               equal(acceptedTerms, account.acceptedTerms) &&
               equal(account_id, account.account_id) &&
               equal(account_name, account.account_name) &&
               equal(address, account.address) &&
               equal(emailAddress, account.emailAddress) &&
               equal(password, account.password) &&
               equal(phoneNumber, account.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(account_id, account_name, password, enabled, emailAddress, phoneNumber, address, acceptedTerms);
    }

    public Long getAccount_id() {
        return account_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public Account setAccount_name(String account_name) {
        this.account_name = account_name;
        return this;
    }

    public Account setAccount_id(Long account_id) {
        this.account_id = account_id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Account setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Account setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Account setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Account setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public Account setAddress(Address address) {
        this.address = address;
        return this;
    }

    public String getAcceptedTerms() {
        return acceptedTerms;
    }

    public Account setAcceptedTerms(String acceptedTerms) {
        this.acceptedTerms = acceptedTerms;
        return this;
    }

}

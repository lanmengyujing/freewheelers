package com.trailblazers.freewheelers.model;

import com.google.common.base.Objects;

import java.math.BigDecimal;

import static com.google.common.base.Objects.equal;

public class CreditCardPayment {
    private String creditCardNumber;
    private String holderName;
    private String expiryDate;
    private String securityCode;
    private BigDecimal amount;
    private CreditCardPaymentStatus paymentStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCardPayment)) return false;

        CreditCardPayment anotherCreditCardPayment = (CreditCardPayment) o;

        return equal(creditCardNumber, anotherCreditCardPayment.getCreditCardNumber()) &&
                equal(holderName, anotherCreditCardPayment.getHolderName()) &&
                equal(expiryDate, anotherCreditCardPayment.getExpiryDate()) &&
                equal(securityCode, anotherCreditCardPayment.getSecurityCode()) &&
                equal(amount, anotherCreditCardPayment.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(creditCardNumber, holderName, expiryDate, securityCode, amount);
    }

    public CreditCardPayment setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
        return this;
    }

    public CreditCardPayment setHolderName(String holderName) {
        this.holderName = holderName;
        return this;
    }

    public CreditCardPayment setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public CreditCardPayment setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
        return this;
    }

    public CreditCardPayment setAmount(BigDecimal amount) {
        this.amount = amount.setScale(2,BigDecimal.ROUND_HALF_EVEN);
        return this;
    }

    public CreditCardPayment setPaymentStatus(CreditCardPaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
        return this;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public CreditCardPaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public String asXML() {
        return "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                "<authorization-request>" +
                "<param key=\"CC_NUM\" value=\"" + creditCardNumber + "\" />" +
                "<param key=\"CC_EXPIRY\" value=\"" + expiryDate + "\" />" +
                "<param key=\"CC_CSC\" value=\"" + securityCode + "\" />" +
                "<param key=\"AMT\" value=\"" + amount + "\" />" +
                "</authorization-request>";
    }
}

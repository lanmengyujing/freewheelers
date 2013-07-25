package com.trailblazers.freewheelers.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Invoice {
    private String paymentType;
    private Date taxDate;
    private Date dueBy;
    private Long orderId;
    private String invoiceNumber;
    private List<InvoiceItem> items;
    private BigDecimal netTotal;
    private BigDecimal vatTotal;
    private BigDecimal dutyTax;
    private BigDecimal grossTotal;
    private Account account;

    public Invoice() {
        items = new ArrayList<InvoiceItem>();
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Date getTaxDate() {
        return taxDate;
    }

    public void setTaxDate(Date taxDate) {
        this.taxDate = taxDate;
    }

    public Date getDueBy() {
        return dueBy;
    }

    public void setDueBy(Date dueBy) {
        this.dueBy = dueBy;
    }

    public BigDecimal getGrossTotal() {
        return grossTotal;
    }

    public void addInvoiceItem(InvoiceItem invoiceItem) {
        items.add(invoiceItem);
    }

    public void setNetTotal(BigDecimal netTotal) {
        this.netTotal = netTotal;
    }

    public BigDecimal getNetTotal() {
        return netTotal;
    }

    public void setVatTotal(BigDecimal vatTotal) {
        this.vatTotal = vatTotal;
    }

    public BigDecimal getVatTotal() {
        return vatTotal;
    }

    public void setDutyTax(BigDecimal dutyTax) {
        this.dutyTax = dutyTax;
    }

    public BigDecimal getDutyTax() {
        return dutyTax;
    }

    public void setGrossTotal(BigDecimal grossTotal) {
        this.grossTotal = grossTotal;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}

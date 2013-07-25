package com.trailblazers.freewheelers.model;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: mmoise
 * Date: 08/07/2013
 * Time: 00:20
 * To change this template use File | Settings | File Templates.
 */
public class PaymentInfo {
    private BigDecimal netTotal;
    private BigDecimal dutyTax;
    private BigDecimal vatTax;
    private BigDecimal grossTotal;

    public BigDecimal getNetTotal() {
        return netTotal;
    }

    public BigDecimal getDutyTax() {
        return dutyTax;
    }

    public BigDecimal getVatTax() {
        return vatTax;
    }

    public PaymentInfo(BigDecimal netTotal, BigDecimal dutyTax, BigDecimal vatTax) {
        this.netTotal = netTotal;
        this.dutyTax = dutyTax;
        this.vatTax = vatTax;
        this.grossTotal = netTotal.add(dutyTax).add(vatTax);
    }

    public BigDecimal getGrossTotal() {
        return grossTotal;
    }
}

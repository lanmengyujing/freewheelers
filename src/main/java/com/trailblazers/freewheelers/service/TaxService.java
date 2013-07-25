package com.trailblazers.freewheelers.service;

import java.math.BigDecimal;

public interface TaxService {

    public BigDecimal getDutyTax(String country, BigDecimal price);

    public BigDecimal getVat(String country, BigDecimal itemPrice);

    BigDecimal getVatRate(String country);
}

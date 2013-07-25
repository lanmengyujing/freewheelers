package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.service.TaxService;

import java.math.BigDecimal;
import java.util.HashMap;

public class TaxServiceImpl implements TaxService {

    private final HashMap<String, BigDecimal> oneItemDutyTaxesMap;

    private final HashMap<String, BigDecimal> vatMap;

    public TaxServiceImpl() {
        oneItemDutyTaxesMap = new HashMap<String, BigDecimal>();
        oneItemDutyTaxesMap.put("CANADA", new BigDecimal(0.07));
        oneItemDutyTaxesMap.put("USA", new BigDecimal(0.09));

        vatMap = new HashMap<String, BigDecimal>();
        vatMap.put("UK", new BigDecimal(0.2));
        vatMap.put("GERMANY", new BigDecimal(0.19));
        vatMap.put("ITALY", new BigDecimal(0.21));
        vatMap.put("FRANCE", new BigDecimal(0.196));
        vatMap.put("USA", new BigDecimal(0));
        vatMap.put("CANADA", new BigDecimal(0));
    }


    public BigDecimal getDutyTax(String country, BigDecimal price) {

        BigDecimal countryDutyTax = oneItemDutyTaxesMap.get(country);

        if (countryDutyTax == null) return new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_EVEN);

        return price.multiply(countryDutyTax).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    public BigDecimal getVat(String country, BigDecimal itemPrice) {

        return vatMap.get(country).multiply(itemPrice).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    @Override
    public BigDecimal getVatRate(String country) {
        return vatMap.get(country).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
}

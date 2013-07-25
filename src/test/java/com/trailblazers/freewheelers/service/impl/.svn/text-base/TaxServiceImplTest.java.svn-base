package com.trailblazers.freewheelers.service.impl;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TaxServiceImplTest {

    private static final String UK = "UK";
    private TaxServiceImpl taxService;

    @Before
    public void setUp() throws Exception {
        taxService = new TaxServiceImpl();
    }

    @Test
    public void shouldComputeDutyTaxForCanada() {

        BigDecimal itemPrice = new BigDecimal(200.00);
        BigDecimal countryDutyTax = new BigDecimal(14).setScale(2, BigDecimal.ROUND_HALF_EVEN);

        TaxServiceImpl taxService = new TaxServiceImpl();

        assertEquals(countryDutyTax, taxService.getDutyTax("CANADA", itemPrice));

    }

    @Test
    public void shouldComputeDutyTaxForUSAForLessThanTwoItems() {

        BigDecimal itemPrice = new BigDecimal(200.00);
        BigDecimal countryDutyTax = new BigDecimal(18).setScale(2, BigDecimal.ROUND_HALF_EVEN);

        TaxServiceImpl taxService = new TaxServiceImpl();

        assertEquals(countryDutyTax, taxService.getDutyTax("USA", itemPrice));

    }

    @Test
    public void shouldReturnZeroForCountriesInEUOrUK() {

        BigDecimal itemPrice = new BigDecimal(200.00);
        BigDecimal countryDutyTax = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_EVEN);

        TaxServiceImpl taxService = new TaxServiceImpl();

        assertEquals(countryDutyTax, taxService.getDutyTax("UK", itemPrice));
    }

    @Test
    public void shouldReturnVatForUK() {
        BigDecimal itemPrice = new BigDecimal(200.00);
        BigDecimal countryVatTax = new BigDecimal(40.00).setScale(2, BigDecimal.ROUND_HALF_EVEN);

        TaxServiceImpl taxService = new TaxServiceImpl();

        assertEquals(countryVatTax, taxService.getVat("UK", itemPrice));
    }

    @Test
    public void shouldReturnVatForGermany() {
        BigDecimal itemPrice = new BigDecimal(200.00);
        BigDecimal countryVatTax = new BigDecimal(38.00).setScale(2, BigDecimal.ROUND_HALF_EVEN);

        TaxServiceImpl taxService = new TaxServiceImpl();

        assertEquals(countryVatTax, taxService.getVat("GERMANY", itemPrice));
    }

    @Test
    public void shouldReturnVatForItaly() {
        BigDecimal itemPrice = new BigDecimal(200.00);
        BigDecimal countryVatTax = new BigDecimal(42.00).setScale(2, BigDecimal.ROUND_HALF_EVEN);

        TaxServiceImpl taxService = new TaxServiceImpl();

        assertEquals(countryVatTax, taxService.getVat("ITALY", itemPrice));
    }

    @Test
    public void shouldReturnVatForFrance() {
        BigDecimal itemPrice = new BigDecimal(100.00);
        BigDecimal countryVatTax = new BigDecimal(19.60).setScale(2, BigDecimal.ROUND_HALF_EVEN);

        TaxServiceImpl taxService = new TaxServiceImpl();

        assertEquals(countryVatTax, taxService.getVat("FRANCE", itemPrice));
    }

    @Test
    public void shouldReturnZeroVatForUSA() {
        BigDecimal itemPrice = new BigDecimal(100.00);
        BigDecimal countryVatTax = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_EVEN);

        TaxServiceImpl taxService = new TaxServiceImpl();

        assertEquals(countryVatTax, taxService.getVat("USA", itemPrice));
    }

    @Test
    public void shouldReturnZeroVatForCanada() {
        BigDecimal itemPrice = new BigDecimal(100.00);
        BigDecimal countryVatTax = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_EVEN);

        assertEquals(countryVatTax, taxService.getVat("CANADA", itemPrice));
    }

    @Test
    public void shouldGetVatRate() throws Exception {
        assertThat(taxService.getVatRate(UK), is(new BigDecimal(0.2).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
    }
}

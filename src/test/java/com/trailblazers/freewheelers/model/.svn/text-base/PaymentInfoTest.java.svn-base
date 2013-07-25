package com.trailblazers.freewheelers.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class PaymentInfoTest {

    @Test
    public void shouldCalculateGrossTotalGivenNetTotalAndTaxes(){
        BigDecimal netTotal = new BigDecimal(10);
        BigDecimal dutyTax  = new BigDecimal(2);
        BigDecimal vatTax  = new BigDecimal(1);

        BigDecimal expectedGrossTotal = new BigDecimal(13);
        PaymentInfo paymentInfo = new PaymentInfo(netTotal,dutyTax,vatTax);

        assertThat(paymentInfo.getGrossTotal(),is(expectedGrossTotal));
    }
}

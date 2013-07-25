package com.trailblazers.freewheelers.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CreditCardPaymentTest {

    private static final String CREDIT_CARD_NUMBER = "4111111111111111";
    private static final String HOLDER_NAME = "Yaodan & Andrew";
    private static final String EXPIRY_DATE = "11-2020";
    private static final String SECURITY_CODE = "534";
    private static final BigDecimal AMOUNT = new BigDecimal(52.04).setScale(2,BigDecimal.ROUND_HALF_EVEN);
    private CreditCardPayment creditCardPayment;

    @Before
    public void setUp() {
        creditCardPayment = createCreditCardPayment();
    }

    @Test
    public void shouldCreateNewCreditCardPayment() {
        // then
        assertThat(creditCardPayment.getCreditCardNumber(), is(CREDIT_CARD_NUMBER));
        assertThat(creditCardPayment.getHolderName(), is(HOLDER_NAME));
        assertThat(creditCardPayment.getExpiryDate(), is(EXPIRY_DATE));
        assertThat(creditCardPayment.getSecurityCode(), is(SECURITY_CODE));
        assertThat(creditCardPayment.getAmount(), is(AMOUNT));
    }

    @Test
    public void shouldBeValueObject() throws Exception {
        // given
        CreditCardPayment anotherCreditCardPayment = createCreditCardPayment();

        // then
        assertThat(creditCardPayment, is(anotherCreditCardPayment));
    }

    @Test
    public void shouldCreateXMLRepresentationOfTheCreditCardPayment() throws Exception {
        // given
        String xml =
                "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                        "<authorization-request>" +
                        "<param key=\"CC_NUM\" value=\"4111111111111111\" />" +
                        "<param key=\"CC_EXPIRY\" value=\"11-2020\" />" +
                        "<param key=\"CC_CSC\" value=\"534\" />" +
                        "<param key=\"AMT\" value=\"52.04\" />" +
                        "</authorization-request>";

        // then
        assertThat(createCreditCardPayment().asXML(), is(xml));
    }

    private CreditCardPayment createCreditCardPayment() {
        return new CreditCardPayment()
                .setCreditCardNumber(CREDIT_CARD_NUMBER)
                .setHolderName(HOLDER_NAME)
                .setExpiryDate(EXPIRY_DATE)
                .setSecurityCode(SECURITY_CODE)
                .setAmount(AMOUNT);
    }
}

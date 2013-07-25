package service.com.trailblazers.freewheelers;

import com.trailblazers.freewheelers.model.CreditCardPayment;
import com.trailblazers.freewheelers.service.impl.HttpRequestService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CreditCardGatewayServiceTest {

    private static final String CREDIT_CARD_NUMBER = "4111111111111111";
    private static final String HOLDER_NAME = "Yaodan & Andrew";
    private static final String EXPIRY_DATE = "11-2020";
    private static final String SECURITY_CODE = "534";
    private static final BigDecimal AMOUNT = new BigDecimal(52.04);
    private HttpRequestService httpRequestService;
    private CreditCardPayment creditCardPayment;

    @Before
    public void setUp() {
        httpRequestService = new HttpRequestService();
        httpRequestService.setGatewayUrl("http://ops.university.thoughtworks.com:4567/authorize");
        creditCardPayment = createCreditCardPayment();
    }

    @Test
    public void shouldGetSuccessResponseForValidCreditCardPayment() throws Exception {
        //then
        assertTrue(httpRequestService.sendRequestToCreditCardGateway(creditCardPayment.asXML()).contains("SUCCESS"));
    }

    @Test
    public void shouldGetRevokedResponseForInvalidCreditCardPayment() throws Exception {
        // given
        creditCardPayment.setSecurityCode("533");

        // then
        assertFalse(httpRequestService.sendRequestToCreditCardGateway(creditCardPayment.asXML()).contains("SUCCESS"));
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

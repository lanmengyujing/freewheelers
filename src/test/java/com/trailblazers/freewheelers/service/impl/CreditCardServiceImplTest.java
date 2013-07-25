package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.model.CreditCardPayment;
import com.trailblazers.freewheelers.model.CreditCardPaymentStatus;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.service.ServiceResult;
import com.trailblazers.freewheelers.web.TaxCalculator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CreditCardServiceImplTest {
    private String VALID_XML_RESPONSE_CONTENT;
    private String TRANSACTION_ID;

    @Mock
    private CreditCardPayment mockedCreditCardPayment;
    @Mock
    private HttpRequestService mockedHttpRequestService;
    @Mock
    private HttpResponse mockedHttpResponse;
    @Mock
    private HttpEntity mockedHttpEntity;
    @Mock
    private ReserveOrder mockedOrder;
    @Mock
    private Item mockedItem;
    @Mock
    private TaxCalculator mockedCalculator;


    private CreditCardServiceImpl creditCardService;

    @Before
    public void setUp() {
        initMocks(this);
        creditCardService = new CreditCardServiceImpl();

        TRANSACTION_ID =  "13248487483";
        VALID_XML_RESPONSE_CONTENT =
                "<authorization-response>" +
                    "<SUCCESS id=\""+
                        TRANSACTION_ID+
                    "\" />" +
                "</authorization-response>";



        when(mockedCreditCardPayment.asXML()).thenReturn("somexml");

        creditCardService.setExtractResponseStatusPattern("<authorization-response><(.*?) id=\\\"(.*)\\\"");
        creditCardService.setHttpRequestService(mockedHttpRequestService);
    }


    @Test
    public void shouldReturnTransactionIdWhenPaymentWasSuccessful() throws Exception {
        when(mockedHttpRequestService.sendRequestToCreditCardGateway(anyString())).thenReturn(VALID_XML_RESPONSE_CONTENT);

        CreditCardPaymentStatus creditCardPaymentStatusSuccessful = CreditCardPaymentStatus.success(TRANSACTION_ID);

        ServiceResult<CreditCardPaymentStatus> creditCardPaymentServiceResult = creditCardService.pay(mockedCreditCardPayment);
        assertEquals(creditCardPaymentServiceResult.getModel(),creditCardPaymentStatusSuccessful);
    }

//    @Test
//    public void shouldReturnGatewayErrorWhenErrorIsSentFromServer(){
//
//    }

    @Test
    public void shouldReturnConnectionErrorWhenHttpClientThrowsException() throws Exception {
        when(mockedHttpRequestService.sendRequestToCreditCardGateway(anyString())).thenThrow(IOException.class, URISyntaxException.class);

        ServiceResult<CreditCardPaymentStatus> creditCardPaymentServiceResult = creditCardService.pay(mockedCreditCardPayment);
        assertEquals(creditCardPaymentServiceResult.getErrors().get("payment"), "error io exception / uri syntax exception");
    }

    @Test
    public void shouldExtractResponseStatusFromXMLResponseString() {
        assertThat(creditCardService.getCreditCardPaymentStatus(VALID_XML_RESPONSE_CONTENT), is(CreditCardPaymentStatus.success(TRANSACTION_ID)));
    }

    @Test
    public void shouldNotExtractResponseStatusForInvalidResponse() {
        assertThat(creditCardService.getCreditCardPaymentStatus("<notExcepted><SUCCESS id=\"13248487483\" />" +
                "</authorization-response>"), is(CreditCardPaymentStatus.unexpected_response()));
    }

    @Test
    public void shouldExtractTransactionId() throws Exception {
        assertThat(creditCardService.getCreditCardPaymentTransactionId(VALID_XML_RESPONSE_CONTENT), is(TRANSACTION_ID));
    }


}

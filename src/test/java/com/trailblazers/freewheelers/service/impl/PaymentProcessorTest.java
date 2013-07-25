package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mail.HtmlMailBuilder;
import com.trailblazers.freewheelers.mail.MailContentRender;
import com.trailblazers.freewheelers.model.*;
import com.trailblazers.freewheelers.service.*;
import com.trailblazers.freewheelers.web.TaxCalculator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class PaymentProcessorTest {

    @Mock
    private CreditCardService fakeCreditCardService;
    @Mock
    private PaidOrderService fakePaidOrderService;

    @Mock
    private MailService fakeMailService;

    @Mock
    private ReserveOrderService fakeReserveOrderService;

    @Mock
    private TaxCalculator fakeTaxCalculator;

    PaymentProcessor paymentProcessor;

    Account account;

    Long accountId;
    private CreditCardPayment creditCardPaymentDetails;
    private List<ReserveOrder> reserveOrders;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        paymentProcessor = new PaymentProcessor(fakeCreditCardService, fakePaidOrderService, fakeReserveOrderService, fakeMailService, fakeTaxCalculator);

        paymentProcessor.setInvoiceHtmlMailBuilder(mock(HtmlMailBuilder.class));

        accountId = 1L;
        account = new Account().setAccount_id(accountId);

        creditCardPaymentDetails = new CreditCardPayment();

        reserveOrders = asList(new ReserveOrder());
        PaymentInfo paymentInfo = new PaymentInfo(new BigDecimal(10), new BigDecimal(2), new BigDecimal(1));
        when(fakeTaxCalculator.getPaymentInfoForOrders(reserveOrders)).thenReturn(paymentInfo);

    }


    @Test
    public void shouldMoveItemsToPaidWhenPaymentSuccessful() throws Exception {

        String aTransactionId = "11111";
        ServiceResult<CreditCardPaymentStatus> successPayment = new ServiceResult<CreditCardPaymentStatus>(new HashMap<String, String>(), CreditCardPaymentStatus.success(aTransactionId));

        PaymentInfo paymentInfo = new PaymentInfo(new BigDecimal(10), new BigDecimal(2), new BigDecimal(1));
        when(fakeTaxCalculator.getPaymentInfoForOrders(reserveOrders)).thenReturn(paymentInfo);
        when(fakeCreditCardService.pay(any(CreditCardPayment.class))).thenReturn(successPayment);

        when(fakeReserveOrderService.findAllOrdersByAccountId(accountId)).thenReturn(reserveOrders);
        when(fakeReserveOrderService.getInvoice(any(ReserveOrder.class))).thenReturn(new Invoice());

        boolean isPaymentSuccessful = paymentProcessor.payForAccount(account, creditCardPaymentDetails);

        assertThat(isPaymentSuccessful, is(true));

        verify(fakeTaxCalculator).getPaymentInfoForOrders(reserveOrders);
        verify(fakeReserveOrderService).findAllOrdersByAccountId(accountId);
        verify(fakeCreditCardService).pay(any(CreditCardPayment.class));
        verify(fakePaidOrderService).moveItemsToPaidForAccountId(accountId);
        verify(fakeMailService).send(any(HtmlMailBuilder.class), any(MailContentRender.class));
    }

    @Test
    public void shouldNotMoveItemsToPaidWhenPaymentFails() throws Exception {


        ServiceResult<CreditCardPaymentStatus> failedPayment = new ServiceResult<CreditCardPaymentStatus>(new HashMap<String, String>(), CreditCardPaymentStatus.unauth());


        when(fakeCreditCardService.pay(any(CreditCardPayment.class))).thenReturn(failedPayment);
        when(fakeReserveOrderService.findAllOrdersByAccountId(accountId)).thenReturn(asList(new ReserveOrder()));

        boolean isPaymentSuccessful = paymentProcessor.payForAccount(account, creditCardPaymentDetails);
        assertThat(isPaymentSuccessful, is(false));

        verify(fakeReserveOrderService).findAllOrdersByAccountId(accountId);
        verify(fakeCreditCardService).pay(any(CreditCardPayment.class));
        verify(fakePaidOrderService, never()).moveItemsToPaidForAccountId(accountId);
        verify(fakeMailService, never()).send(any(HtmlMailBuilder.class), any(MailContentRender.class));

    }


    @Test
    public void shouldNotMoveItemsToPaidWhenErrorArises() throws Exception {

        HashMap<String, String> errors = new HashMap<String, String>();
        errors.put("error", "some error");
        ServiceResult<CreditCardPaymentStatus> paymentWithError = new ServiceResult<CreditCardPaymentStatus>(errors, null);


        when(fakeCreditCardService.pay(any(CreditCardPayment.class))).thenReturn(paymentWithError);
        when(fakeReserveOrderService.findAllOrdersByAccountId(accountId)).thenReturn(asList(new ReserveOrder()));

        boolean isPaymentSuccessful = paymentProcessor.payForAccount(account, creditCardPaymentDetails);
        assertThat(isPaymentSuccessful, is(false));

        verify(fakeReserveOrderService).findAllOrdersByAccountId(accountId);
        verify(fakeCreditCardService).pay(any(CreditCardPayment.class));
        verify(fakePaidOrderService, never()).moveItemsToPaidForAccountId(accountId);
        verify(fakeMailService, never()).send(any(HtmlMailBuilder.class), any(MailContentRender.class));


    }
}


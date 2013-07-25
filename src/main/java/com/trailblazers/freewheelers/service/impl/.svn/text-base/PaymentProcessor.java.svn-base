package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mail.HtmlMailBuilder;
import com.trailblazers.freewheelers.mail.InvoiceMailContentRender;
import com.trailblazers.freewheelers.model.*;
import com.trailblazers.freewheelers.service.*;
import com.trailblazers.freewheelers.web.TaxCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PaymentProcessor {
    public static final String PAYMENT_TYPE = "Credit Card";
    @Autowired
    private CreditCardService creditCardService;
    @Autowired
    private PaidOrderService paidOrderService;
    @Autowired
    private ReserveOrderService reserveOrderService;
    @Autowired
    private MailService mailService;
    @Autowired
    private TaxCalculator taxCalculator;


    @Autowired
    @Qualifier("invoiceMailBuilder")
    private HtmlMailBuilder invoiceHtmlMailBuilder;

    private ItemService itemService;

    public PaymentProcessor() {
    }

    public PaymentProcessor(CreditCardService creditCardService, PaidOrderService paidOrderService, ReserveOrderService reserveOrderService, MailService mailService, TaxCalculator taxCalculator) {
        this.creditCardService = creditCardService;
        this.paidOrderService = paidOrderService;
        this.reserveOrderService = reserveOrderService;
        this.mailService = mailService;
        this.taxCalculator = taxCalculator;

    }

    public void setInvoiceHtmlMailBuilder(HtmlMailBuilder invoiceHtmlMailBuilder) {
        this.invoiceHtmlMailBuilder = invoiceHtmlMailBuilder;
    }

    public boolean payForAccount(Account account, CreditCardPayment creditCardPaymentDetails) {

        List<ReserveOrder> reserveOrders = reserveOrderService.findAllOrdersByAccountId(account.getAccount_id());

        BigDecimal moneyToBePaid = taxCalculator.getPaymentInfoForOrders(reserveOrders).getGrossTotal();

        CreditCardPayment creditCardPayment = getCreditCardPayment(creditCardPaymentDetails, moneyToBePaid);

        ServiceResult<CreditCardPaymentStatus> paymentStatus = creditCardService.pay(creditCardPayment);


        if (hasNoErrors(paymentStatus) && paymentStatus.getModel().isSuccess()) {
            paidOrderService.moveItemsToPaidForAccountId(account.getAccount_id());
            //TODO: send invoice for all the orders
            sendInvoice(reserveOrders.get(0));
            return true;
        } else {
            return false;
        }

    }

    private CreditCardPayment getCreditCardPayment(CreditCardPayment creditCardPaymentDetails, BigDecimal moneyToBePaid) {
        CreditCardPayment creditCardPayment = new CreditCardPayment();
        creditCardPayment.setCreditCardNumber(creditCardPaymentDetails.getCreditCardNumber());
        creditCardPayment.setExpiryDate(creditCardPaymentDetails.getExpiryDate());
        creditCardPayment.setHolderName(creditCardPaymentDetails.getHolderName());
        creditCardPayment.setSecurityCode(creditCardPaymentDetails.getSecurityCode());
        creditCardPayment.setAmount(moneyToBePaid);
        return creditCardPayment;
    }

    private void sendInvoice(ReserveOrder order) {
        Invoice invoice = createInvoice(order);
        mailService.send(invoiceHtmlMailBuilder, new InvoiceMailContentRender(invoice));
    }

    private Invoice createInvoice(ReserveOrder order) {
        Invoice invoice = reserveOrderService.getInvoice(order);
        invoice.setPaymentType(PAYMENT_TYPE);
        invoice.setDueBy(new Date());
        invoice.setTaxDate(new Date());
        return invoice;
    }

    private boolean hasNoErrors(ServiceResult<CreditCardPaymentStatus> serviceResult){
        return serviceResult.getErrors().isEmpty();
    }
}

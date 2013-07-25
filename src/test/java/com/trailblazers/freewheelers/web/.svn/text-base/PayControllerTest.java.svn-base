package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.CreditCardPayment;
import com.trailblazers.freewheelers.model.PaymentInfo;
import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.service.*;
import com.trailblazers.freewheelers.service.impl.PaymentProcessor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class PayControllerTest {

    private PayController payController;
    @Mock
    private CreditCardService mockedCreditCardService;
    @Mock
    private ItemService mockedItemService;
    @Mock
    private ReserveOrderService mockedReserveOrderService;
    @Mock
    private TaxCalculator mockedTaxCalculator;
    @Mock
    private MailService mailService;
    @Mock
    private Principal principal;
    @Mock
    private ReserveOrder mockedReserveOrder;
    @Mock
    private CreditCardPayment mockedCreditCardPayment;
    @Mock
    private PaymentProcessor mockedPaymentProcessor;
    @Mock
    private AccountService mockedAccountService;

    String accountName;
    Long accountId;
    List<ReserveOrder> reserveOrders;
    PaymentInfo paymentInfo;
    Account account;

    @Before
    public void setUp() {
        initMocks(this);
        payController = new PayController();

        accountId = 1L;
        accountName = "account_name";
        account = new Account();
        account.setAccount_id(accountId);

        reserveOrders = asList(new ReserveOrder());

        paymentInfo = new PaymentInfo(new BigDecimal(10),new BigDecimal(1),new BigDecimal(1));

        when(mockedReserveOrderService.currentOrderAvailableForAccountId(accountId)).thenReturn(true);
        when(mockedReserveOrderService.findAllOrdersByAccountId(accountId)).thenReturn(reserveOrders);
        when(mockedTaxCalculator.getPaymentInfoForOrders(reserveOrders)).thenReturn(paymentInfo);
        when(principal.getName()).thenReturn(accountName);
        when(mockedAccountService.getAccountIdByName(accountName)).thenReturn(account);


        payController.setPaymentProcessor(mockedPaymentProcessor);
        payController.setReserveOrderService(mockedReserveOrderService);
        payController.setTaxCalculator(mockedTaxCalculator);
        payController.setAccountService(mockedAccountService);
    }

    @Test
    public void shouldGetTheListOfReservedItems(){
        payController.goToPaymentPage(new ModelMap(),principal);
        verify(principal).getName();
        verify(mockedAccountService).getAccountIdByName(accountName);
        verify(mockedReserveOrderService).findAllOrdersByAccountId(accountId);
    }

    @Test
    public void shouldGetPaymentInfoToShowItInThePaymentPage(){
        payController.goToPaymentPage(new ModelMap(),principal);
        verify(mockedTaxCalculator).getPaymentInfoForOrders(reserveOrders);
    }

    @Test
    public void shouldHaveAnPaymentInfoObjectWhenItShowsThePaymentPage(){
        ModelMap model = new ModelMap();
        payController.goToPaymentPage(model,principal);

        assertTrue(model.get("paymentInfo").equals(paymentInfo));
    }

    @Test
    public void shouldHaveACreditCardDetailsAttributeOnTheModel(){
        ModelMap model = new ModelMap();
        payController.goToPaymentPage(model,principal);

        assertTrue(model.containsKey("creditCardDetails"));
    }


    @Test
    public void shouldTestForTheAvailabilityOfTheOrder(){
        payController.payCreditCard(mockedCreditCardPayment,new ModelMap(),principal);
        verify(mockedReserveOrderService).currentOrderAvailableForAccountId(accountId);
    }

    @Test
    public void shouldTryToPayForTheCurrentOrder(){
        payController.payCreditCard(mockedCreditCardPayment,new ModelMap(),principal);
        verify(mockedPaymentProcessor).payForAccount(account,mockedCreditCardPayment);
    }

    @Test
    public void shouldReturnSuccessUrlForValidCreditCard() throws Exception {
      
        when(mockedReserveOrderService.currentOrderAvailableForAccountId(accountId)).thenReturn(true);
        when(mockedPaymentProcessor.payForAccount(account, mockedCreditCardPayment)).thenReturn(true);
        ModelAndView view = payController.payCreditCard(mockedCreditCardPayment, new ModelMap(),principal);

        assertTrue(view.getViewName().startsWith("redirect:/pay/success"));
    }


    @Test
    public void shouldReturnFailureUrlForInvalidCreditCard() throws Exception {
        when(mockedPaymentProcessor.payForAccount(account, mockedCreditCardPayment)).thenReturn(false);
        ModelAndView view = payController.payCreditCard(mockedCreditCardPayment, new ModelMap(),principal);

        assertThat(view.getViewName(), is("/pay/creditCardPayment"));
    }

    @Test
    public void shouldHaveTheCreditCardDetailsOnTheModelIfPaymentFails(){
        when(mockedPaymentProcessor.payForAccount(account, mockedCreditCardPayment)).thenReturn(false);
        ModelMap model = new ModelMap();
        payController.payCreditCard(mockedCreditCardPayment, model, principal);

        assertTrue(model.get("creditCardDetails").equals(mockedCreditCardPayment));

    }


    @Test
    public void shouldHaveErrorsOnTheModelIfPaymentFails(){
        when(mockedPaymentProcessor.payForAccount(account, mockedCreditCardPayment)).thenReturn(false);
        ModelMap model = new ModelMap();
        payController.payCreditCard(mockedCreditCardPayment, model, principal);

        assertTrue(model.get("errors").equals("Your credit card was rejected!"));

    }

    @Test
    public void shouldHaveThePaymentInfoOnTheModelIfPaymentFails(){
        when(mockedPaymentProcessor.payForAccount(account, mockedCreditCardPayment)).thenReturn(false);
        ModelMap model = new ModelMap();
        payController.payCreditCard(mockedCreditCardPayment, model, principal);

        assertTrue(model.get("paymentInfo").equals(paymentInfo));

    }

    @Test
    public void shouldShowOutOfStockErrorForItemWithZeroQuantity() throws Exception {
        when(mockedReserveOrderService.currentOrderAvailableForAccountId(accountId)).thenReturn(false);
        ModelMap model = new ModelMap();
        payController.payCreditCard(mockedCreditCardPayment, model, principal);

        assertEquals(model.get("errors"), "Sorry! The item is out of stock");

    }

    @Test
    public void shuouldRenderOutOfStockErrorPageWhenItemWithZeroQuantity() throws Exception {
        when(mockedReserveOrderService.currentOrderAvailableForAccountId(accountId)).thenReturn(false);
        ModelAndView view = payController.payCreditCard(mockedCreditCardPayment, new ModelMap(), principal);

        assertThat(view.getViewName(), is("/pay/itemOutOfStock"));
    }

    @Test
    public void shouldRemoveOrderFromShoppingCartWhenOneItemWithZeroQuantity() throws Exception {
        when(mockedReserveOrderService.currentOrderAvailableForAccountId(accountId)).thenReturn(false);
        payController.payCreditCard(mockedCreditCardPayment, new ModelMap(), principal);

        verify(mockedReserveOrderService).deleteOrdersForAccountId(accountId);
    }

    @Test
    public void ShouldNotPayWhenQuantityOfItemIsZero() throws Exception {
        when(mockedReserveOrderService.currentOrderAvailableForAccountId(accountId)).thenReturn(false);
        payController.payCreditCard(mockedCreditCardPayment, new ModelMap(), principal);

        verify(mockedPaymentProcessor, never()).payForAccount(account, mockedCreditCardPayment);
    }
}

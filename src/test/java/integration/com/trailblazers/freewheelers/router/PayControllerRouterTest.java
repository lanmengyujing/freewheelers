package integration.com.trailblazers.freewheelers.router;

import com.trailblazers.freewheelers.model.*;
import com.trailblazers.freewheelers.service.CreditCardService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.MailService;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import com.trailblazers.freewheelers.service.impl.PaymentProcessor;
import com.trailblazers.freewheelers.web.PayController;
import com.trailblazers.freewheelers.web.TaxCalculator;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.ui.ModelMap;

import java.math.BigDecimal;
import java.security.Principal;

import static com.trailblazers.freewheelers.model.OrderStatus.NEW;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class PayControllerRouterTest {

    public static final String PAY_CREDIT_CARD_URL = "/pay/creditCard";

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
    private MailService mockedMailService;
    @Mock
    private Principal principal;
    @Mock
    private ReserveOrder mockedReserveOrder;
    @Mock
    private CreditCardPayment mockedCreditCardPayment;
    @Mock
    private PaymentProcessor mockedPaymentProcessor;

    @Before
    public void setUp() {
        initMocks(this);
        payController = new PayController();

        when(mockedReserveOrder.getCreditCardPayment()).thenReturn(mockedCreditCardPayment);
        when(mockedReserveOrderService.get(anyLong())).thenReturn(mockedReserveOrder);
        when(mockedReserveOrderService.getInvoice(any(ReserveOrder.class))).thenReturn(new Invoice());
        when(mockedItemService.get(anyLong())).thenReturn(new Item().setPrice(new BigDecimal(0)).setQuantity(1L));
        when(mockedTaxCalculator.getDutyTax(any(ReserveOrder.class))).thenReturn(new BigDecimal(0));
        when(mockedTaxCalculator.getVat(any(ReserveOrder.class))).thenReturn(new BigDecimal(0));

        payController.setPaymentProcessor(mockedPaymentProcessor);
        payController.setReserveOrderService(mockedReserveOrderService);
        payController.setItemService(mockedItemService);
        payController.setTaxCalculator(mockedTaxCalculator);
    }

    @Test
    @Ignore
    public void shouldRenderPaymentOptionsPage() throws Exception {
        when(mockedReserveOrder.getStatus()).thenReturn(NEW);
        when(mockedReserveOrderService.isReservedByCurrentAccount(anyString(), any(ReserveOrder.class))).thenReturn(true);
        when(mockedPaymentProcessor.payForAccount(any(Account.class), any(CreditCardPayment.class))).thenReturn(false);

        standaloneSetup(payController)
                .build()
                .perform(get(PAY_CREDIT_CARD_URL).principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("/pay/creditCardPayment"));
    }

    @Test
    @Ignore
    public void shouldRenderToPaymentSuccessPageForValidCreditCard() throws Exception {
        when(mockedPaymentProcessor.payForAccount(any(Account.class), any(CreditCardPayment.class))).thenReturn(true);

        standaloneSetup(payController)
                .build()
                .perform(post(PAY_CREDIT_CARD_URL))
                .andExpect(
                        new ResultMatcher() {
                            public void match(MvcResult result) {
                                assertTrue("Redirected URL", result.getResponse().getRedirectedUrl().contains("/pay/success"));
                            }
                        });
    }

    @Test
    @Ignore
    public void shouldRenderToPaymentFailurePageForInvalidCreditCard() throws Exception {
        // given
        //when(mockedCreditCardService.pay(mockedReserveOrder)).thenReturn(mockedReserveOrder);

        // then
        standaloneSetup(payController)
                .build()
                .perform(post(PAY_CREDIT_CARD_URL))
                .andExpect(view().name("/pay/creditCardPayment"));
    }

    @Test
    public void shouldCallGoToPaymentPageWhenAGetIsSent() throws Exception{
        PayController mockedPayController = mock(PayController.class);
        when(mockedPayController.goToPaymentPage(any(ModelMap.class),any(Principal.class))).thenReturn("called");
        standaloneSetup(mockedPayController)
                .build()
                .perform(get(PAY_CREDIT_CARD_URL).principal(principal))
                .andExpect(view().name("called"));

    }
}




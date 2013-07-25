package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.CreditCardPayment;
import com.trailblazers.freewheelers.model.PaymentInfo;
import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import com.trailblazers.freewheelers.service.impl.AccountServiceImpl;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import com.trailblazers.freewheelers.service.impl.PaymentProcessor;
import com.trailblazers.freewheelers.service.impl.ReserveOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@SessionAttributes({"cartCount"})
public class PayController {
    private static final String SUCCESS_URL = "/pay/success";
    private static final String SUCCESS_PAGE = "/pay/successPage";
    private static final String FAILURE_URL = "/pay/failure";
    private static final String FAILURE_PAGE = "/pay/failurePage";
    private static final String CREDIT_CARD_PAYMENT_URL = "/pay/creditCard";
    private static final String CREDIT_CARD_PAYMENT_PAGE = "/pay/creditCardPayment";
    private static final String ITEM_OUT_OF_STOCK = "/pay/itemOutOfStock";
    private ItemService itemService;
    private ReserveOrderService reserveOrderService;
    private TaxCalculator taxCalculator;
    private PaymentProcessor paymentProcessor;
    private AccountService accountService;

    public PayController() {
        itemService = new ItemServiceImpl();
        reserveOrderService = new ReserveOrderServiceImpl();
        taxCalculator = new TaxCalculator();
        accountService = new AccountServiceImpl();
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = CREDIT_CARD_PAYMENT_URL, method = RequestMethod.GET)
    public String goToPaymentPage(ModelMap model, Principal principal) {

        PaymentInfo paymentInfo = getPaymentInfo(principal);

        CreditCardPayment emptyCreditCardDetails = new CreditCardPayment();
        model.put("paymentInfo", paymentInfo);
        model.put("creditCardDetails", emptyCreditCardDetails);

        return CREDIT_CARD_PAYMENT_PAGE;
    }

    private PaymentInfo getPaymentInfo(Principal principal) {
        Account account = getAccount(principal);
        List<ReserveOrder> reserveOrders = reserveOrderService.findAllOrdersByAccountId(account.getAccount_id());
        return taxCalculator.getPaymentInfoForOrders(reserveOrders);
    }

    private Account getAccount(Principal principal) {
        String accountName = principal.getName();
        return accountService.getAccountIdByName(accountName);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = CREDIT_CARD_PAYMENT_URL, method = RequestMethod.POST)
    public ModelAndView payCreditCard(@ModelAttribute CreditCardPayment creditCardPayment, ModelMap model, Principal principal) {

        Account account = getAccount(principal);
        ModelAndView modelAndView = null;
        if (reserveOrderService.currentOrderAvailableForAccountId(account.getAccount_id())) {
            if (paymentProcessor.payForAccount(account, creditCardPayment)) {
                modelAndView = new ModelAndView("redirect:" + SUCCESS_URL);
            } else {
                model.put("creditCardDetails", creditCardPayment);
                model.put("paymentInfo", getPaymentInfo(principal));
                model.put("errors", "Your credit card was rejected!");
                modelAndView = new ModelAndView(CREDIT_CARD_PAYMENT_PAGE, model);
            }
        } else {
            reserveOrderService.deleteOrdersForAccountId(account.getAccount_id());
            model.put("errors", "Sorry! The item is out of stock");
            modelAndView = new ModelAndView(ITEM_OUT_OF_STOCK, model);
        }
        model.addAttribute("cartCount", reserveOrderService.getQuantityOfItemsInCart(account.getAccount_id()));
        modelAndView.addAllObjects(model);
        return modelAndView;

    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = SUCCESS_URL, method = RequestMethod.GET)
    public String paymentSuccess(ModelMap modelMap, String orderId) {
        modelMap.put("orderId", orderId);
        return SUCCESS_PAGE;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = FAILURE_URL, method = RequestMethod.GET)
    public String paymentFailure() {
        return FAILURE_PAGE;
    }

    @Autowired
    public void setPaymentProcessor(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @Autowired
    public void setReserveOrderService(ReserveOrderService reserveOrderService) {
        this.reserveOrderService = reserveOrderService;
    }

    @Autowired
    public void setTaxCalculator(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}

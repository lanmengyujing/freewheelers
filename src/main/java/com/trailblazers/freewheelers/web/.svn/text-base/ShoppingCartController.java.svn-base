package com.trailblazers.freewheelers.web;

import com.google.common.base.Strings;
import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@SessionAttributes({"cartCount"})
public class ShoppingCartController {
    static final String URL = "/shoppingCart";
    static final String DELETE_URL = "/shoppingCart/delete";

    @Autowired
    private AccountService accountService;
    @Autowired
    private ReserveOrderService reserveOrderService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private TaxCalculator taxCalculator;

    @RequestMapping(value = URL, method = RequestMethod.POST, params = "reserve=Reserve Item")
    public String addItemToCart(Model model, Principal principal, @ModelAttribute Item item) {
        String userName = principal.getName();
        Account account = accountService.getAccountIdByName(userName);
        String URL = reserveItem(model, item, account);
        model.addAttribute("cartCount", reserveOrderService.getQuantityOfItemsInCart(account.getAccount_id()));
        if (URL.equalsIgnoreCase("reserved"))
            return viewShoppingCart(model, principal);
        return URL;
    }


    @RequestMapping(value = URL, method = RequestMethod.GET)
    public String get(Model model, Principal principal) {
        String userName = principal.getName();
        Account account = accountService.getAccountIdByName(userName);
        model.addAttribute("cartCount", reserveOrderService.getQuantityOfItemsInCart(account.getAccount_id()));
        return viewShoppingCart(model, principal);
    }

    private String viewShoppingCart(Model model, Principal principal) {
        Account account = accountService.getAccountIdByName(principal.getName());
        List<ReserveOrder> orders = getReserveOrdersForAccount(account);

        //ItemGrid itemGrid = new ItemGrid(items);
        ReserveOrderGrid reserveOrderGrid = new ReserveOrderGrid(orders);
        model.addAttribute("reserveOrderGrid", reserveOrderGrid);

        return "shoppingCart";
    }

    public String reserveItem(Model model, Item item, Account account) {

        ReserveOrder reserveOrder = reserveOrderService.createReserveOrder(account, item);

        if (Strings.isNullOrEmpty(account.getAddress().getCountry())) {
            return "reserveOrderWithNoCountrySetup";
        }

        model.addAttribute("item", item);
        model.addAttribute("account", account);

        reserveOrderService.save(reserveOrder);

        model.addAttribute("duty_tax", taxCalculator.getDutyTax(reserveOrder));
        model.addAttribute("vat", taxCalculator.getVat(reserveOrder));
        model.addAttribute("order", reserveOrder);

        return "reserved";
    }


    protected List<ReserveOrder> getReserveOrdersForAccount(Account account) {
        List<ReserveOrder> reserveOrders = reserveOrderService.findAllOrdersByAccountId(account.getAccount_id());

        for (ReserveOrder reserveOrder : reserveOrders) {
            reserveOrder.setItem(itemService.get(reserveOrder.getItem_id()));

        }
        return reserveOrders;
    }

    @RequestMapping(value = DELETE_URL, method = RequestMethod.POST)
    public String deleteItem(@RequestParam("orderId") String orderId, Principal principal) {


        ReserveOrder reserveOrder = reserveOrderService.get(Long.decode(orderId));
        reserveOrderService.delete(reserveOrder);

        return "redirect:/shoppingCart";
    }

    public void setReserveOrderService(ReserveOrderService reserveOrderService) {
        this.reserveOrderService = reserveOrderService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}

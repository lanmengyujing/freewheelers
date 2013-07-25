package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.OrderStatus;
import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.PaidOrderService;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static java.lang.Long.valueOf;

@Controller
public class AdminController {

    static final String URL = "/admin";
    static final String PAGE = "adminProfile";

    @Autowired
    private ReserveOrderService reserveOrderService;
    @Autowired
    private ItemService itemService;


    @Autowired
    private PaidOrderService paidOrderService;


    @Autowired

    private AccountService accountService;

    public void setPaidOrderService(PaidOrderService paidOrderService) {
        this.paidOrderService = paidOrderService;
    }

    @RequestMapping(value = URL, method = RequestMethod.GET)
    public String get(Model model) {
        model.addAttribute("paidOrders", paidOrderService.getAllPaidOrders());
        return PAGE;
    }

    @RequestMapping(value = URL, method = RequestMethod.POST, params = "save=Save Changes")
    public String updateOrder(Model model, String status, String orderId, String note, Long paidOrderID) {
        ReserveOrder reserveOrder = getReserveOrder(status, orderId, note);
        reserveOrderService.updateOrderDetails(reserveOrder, paidOrderID);
        get(model);
        return PAGE;
    }

    private ReserveOrder getReserveOrder(String status, String orderId, String note) {
        ReserveOrder reserveOrder = new ReserveOrder();
        reserveOrder.setOrder_id(valueOf(orderId));
        reserveOrder.setStatus(OrderStatus.valueOf(status));
        reserveOrder.setNote(note);
        return reserveOrder;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public void setReserveOrderService(ReserveOrderService reserveOrderService) {
        this.reserveOrderService = reserveOrderService;
    }

}

package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.PaymentInfo;
import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.TaxService;
import com.trailblazers.freewheelers.service.impl.AccountServiceImpl;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import com.trailblazers.freewheelers.service.impl.TaxServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public class TaxCalculator {

    private final AccountService accountService;
    private final ItemService itemService;
    private final TaxService taxService;

    @Autowired
    public TaxCalculator(AccountService accountService, ItemService itemService, TaxService taxService) {
        this.taxService = taxService;
        this.accountService = accountService;
        this.itemService = itemService;

    }

    public TaxCalculator() {
        this.accountService = new AccountServiceImpl();
        this.taxService = new TaxServiceImpl();
        this.itemService = new ItemServiceImpl();
    }

    public BigDecimal getDutyTax(ReserveOrder reserveOrder) {

        Account account = accountService.get(reserveOrder.getAccount_id());
        Item item = itemService.get(reserveOrder.getItem_id());

        return taxService.getDutyTax(account.getAddress().getCountry(), item.getPrice());
    }

    public BigDecimal getVat(ReserveOrder reserveOrder) {
        Account account = accountService.get(reserveOrder.getAccount_id());
        Item item = itemService.get(reserveOrder.getItem_id());

        return taxService.getVat(account.getAddress().getCountry(), item.getPrice());

    }

    //TODO: test&implement
    public PaymentInfo getPaymentInfoForOrders(List<ReserveOrder> reserveOrders) {
        return new PaymentInfo(new BigDecimal(10),new BigDecimal(1),new BigDecimal(1));

    }
}

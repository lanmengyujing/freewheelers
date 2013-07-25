package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Address;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.TaxService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TaxCalculatorTest {

    public static final String UK = "UK";
    private AccountService mockedAccountService;
    private ItemService mockedItemService;
    private TaxService mockedTaxService;
    public static final String USA = "USA";

    @Before
    public void setUp() throws Exception {
        mockedAccountService = mock(AccountService.class);
        mockedItemService = mock(ItemService.class);
        mockedTaxService = mock(TaxService.class);
    }

    @Test
    public void shouldComputeDutyTaxForOrder() {

        long someAccountId = 1;
        long someItemId = 2;

        BigDecimal price = new BigDecimal(100);
        BigDecimal expected_duty_tax_amount = new BigDecimal(9);

        Address someAddressInUSA = new Address();
        someAddressInUSA.setCountry(USA);
        Account someAccount = new Account().setAddress(someAddressInUSA);

        when(mockedAccountService.get(someAccountId)).thenReturn(someAccount);

        Item someItem = new Item().setPrice(price);

        when(mockedItemService.get(someItemId)).thenReturn(someItem);

        TaxCalculator taxCalculator = new TaxCalculator(mockedAccountService, mockedItemService, mockedTaxService);

        ReserveOrder reserveOrder = new ReserveOrder().setAccount_id(someAccountId).setItem_id(someItemId);

        when(mockedTaxService.getDutyTax(USA, price)).thenReturn(expected_duty_tax_amount);

        BigDecimal dutyTaxValue = taxCalculator.getDutyTax(reserveOrder);

        verify(mockedTaxService).getDutyTax(USA, price);

        assertEquals(expected_duty_tax_amount, dutyTaxValue);


    }

    @Test
    public void shouldComputeVatForOrder() {

        long someAccountId = 1;
        long someItemId = 2;

        BigDecimal price = new BigDecimal(100);
        BigDecimal expected_vat_amount = new BigDecimal(20);

        Address someAddressInUK = new Address();
        someAddressInUK.setCountry(UK);
        Account someAccount = new Account().setAddress(someAddressInUK);

        when(mockedAccountService.get(someAccountId)).thenReturn(someAccount);

        Item someItem = new Item().setPrice(price);

        when(mockedItemService.get(someItemId)).thenReturn(someItem);

        TaxCalculator taxCalculator = new TaxCalculator(mockedAccountService, mockedItemService, mockedTaxService);

        ReserveOrder reserveOrder = new ReserveOrder().setAccount_id(someAccountId).setItem_id(someItemId);

        when(mockedTaxService.getVat(UK, price)).thenReturn(expected_vat_amount);

        BigDecimal vatValue = taxCalculator.getVat(reserveOrder);

        verify(mockedTaxService).getVat(UK, price);

        assertEquals(expected_vat_amount, vatValue);


    }

    @Ignore("TODO: part of computing total cost for multiple items -> it should test getPaymentInfoForOrders")
    @Test
    public void shouldReturnTobePaidMoneyForOrders() throws Exception {
        List<ReserveOrder> reserveOrders = new ArrayList<ReserveOrder>();
        ReserveOrder firstReserveOrder = new ReserveOrder();
        ReserveOrder secondReserveOrder = new ReserveOrder();

        reserveOrders.add(firstReserveOrder);
        reserveOrders.add(secondReserveOrder);


    }
}

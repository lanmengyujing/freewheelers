package com.trailblazers.freewheelers.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class ReserveOrderTest {

    private ReserveOrder reserveOrder;

    @Before
    public void setUp() {
        reserveOrder = new ReserveOrder();
    }

    @Test
    public void shouldCreateAnReservedOrderWithAccountAndItem() {
        long account_id = (long) 1;
        reserveOrder.setAccount_id(account_id);
        reserveOrder.setItem_quantity((long) 1);
        assertNotNull(reserveOrder);
        assertNotNull(reserveOrder.getAccount_id());
        assertNotNull(reserveOrder.getItem_quantity());
    }

    @Test
    public void shouldCalculateTotal() throws Exception {
        BigDecimal vat = new BigDecimal(100);
        BigDecimal duty = new BigDecimal(20);
        BigDecimal price = new BigDecimal(1000);
        reserveOrder.setVatTax(vat).setDutyTax(duty).setPrice(price);
        assertThat(reserveOrder.getTotal(), is(new BigDecimal(1120)));
    }
}

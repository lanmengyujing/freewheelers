package com.trailblazers.freewheelers.model;

import org.junit.Test;

import java.util.Date;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PaidOrderTest {
    @Test
    public void shouldTestWhetherPaidOrderIsCreated() throws Exception {
        ReserveOrder reserveOrder = new ReserveOrder(2L, 1L, new Date());
        PaidOrder paidOrder = new PaidOrder();
        paidOrder.setOrders(asList(reserveOrder));
        paidOrder.setAccount_id(2L);

        assertThat(paidOrder.getOrders(), is(asList(reserveOrder)));

    }


    @Test
    public void shouldBePaidIfAllReserveOrdersArePaid() throws Exception {
        ReserveOrder reserveOrder1 = new ReserveOrder(1L,1L,new Date());
        reserveOrder1.setStatus(OrderStatus.PAID);
        ReserveOrder reserveOrder2 = new ReserveOrder(2L,2L,new Date());
        reserveOrder1.setStatus(OrderStatus.PAID);

        PaidOrder paidOrder = new PaidOrder();
        paidOrder.setOrders(asList(reserveOrder1, reserveOrder2));

        assertThat(paidOrder.getStatus(), is(OrderStatus.PAID));
    }

    @Test
    public void shouldBePaidIfOnlySomeReserveOrdersAreReadyToShip() throws Exception {
        ReserveOrder reserveOrder1 = new ReserveOrder(1L,1L,new Date());
        reserveOrder1.setStatus(OrderStatus.PAID);
        ReserveOrder reserveOrder2 = new ReserveOrder(2L,2L,new Date());
        reserveOrder1.setStatus(OrderStatus.READY_FOR_SHIPMENT);

        PaidOrder paidOrder = new PaidOrder();
        paidOrder.setOrders(asList(reserveOrder1, reserveOrder2));

        assertThat(paidOrder.getStatus(), is(OrderStatus.PAID));
    }

    @Test
    public void shouldBeReadyForShipmentIfAllReserveOrdersAreReadyToShip() throws Exception {
        ReserveOrder reserveOrder1 = new ReserveOrder(1L,1L,new Date());
        reserveOrder1.setStatus(OrderStatus.READY_FOR_SHIPMENT);
        ReserveOrder reserveOrder2 = new ReserveOrder(2L,2L,new Date());
        reserveOrder2.setStatus(OrderStatus.READY_FOR_SHIPMENT);

        PaidOrder paidOrder = new PaidOrder();
        paidOrder.setOrders(asList(reserveOrder1, reserveOrder2));

        assertThat(paidOrder.getStatus(), is(OrderStatus.READY_FOR_SHIPMENT));
    }
}

package com.trailblazers.freewheelers.service.impl;

import com.google.common.collect.ImmutableList;
import com.trailblazers.freewheelers.mail.HtmlMailBuilder;
import com.trailblazers.freewheelers.mail.MailContentRender;
import com.trailblazers.freewheelers.mappers.AccountMapper;
import com.trailblazers.freewheelers.mappers.ItemMapper;
import com.trailblazers.freewheelers.mappers.PaidOrderMapper;
import com.trailblazers.freewheelers.mappers.ReserveOrderMapper;
import com.trailblazers.freewheelers.model.*;
import org.apache.ibatis.session.SqlSession;
import org.hamcrest.beans.HasPropertyWithValue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ReserveOrderServiceImplTest {
    @Mock
    ItemMapper mockedItemMapper;
    @Mock
    AccountMapper mockedAccountMapper;
    @Mock
    ReserveOrderMapper mockedReserveOrderMapper;
    @Mock
    PaidOrderMapper mockedPaidOrderMapper;
    @Mock
    MailServiceImpl mockedMailService;
    @Mock
    PaidOrder mockedPaidOrder;
    @Mock
    HtmlMailBuilder mockedHtmlBuilder;
    @Mock
    MailContentRender mockedMailContentRender;
    @Mock
    private SqlSession sqlSession;

    private ReserveOrderServiceImpl reserveOrderService;
    private ReserveOrder order;
    private PaidOrder paidOrder;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(sqlSession.getMapper(ReserveOrderMapper.class)).thenReturn(mockedReserveOrderMapper);
        when(sqlSession.getMapper(ItemMapper.class)).thenReturn(mockedItemMapper);
        when(sqlSession.getMapper(AccountMapper.class)).thenReturn(mockedAccountMapper);
        when(sqlSession.getMapper(PaidOrderMapper.class)).thenReturn(mockedPaidOrderMapper);


        reserveOrderService = new ReserveOrderServiceImpl(sqlSession);

        reserveOrderService.setMailService(mockedMailService);
        reserveOrderService.setStatusMailBuilder(mockedHtmlBuilder);


        Item item = new Item();
        item.setItemId(new Long(123)).setName("item").setPrice(BigDecimal.TEN);
        when(mockedItemMapper.get(new Long(123))).thenReturn(item);
        Address address = new Address("Street One", "Street Two", "City", "State", "UK", "Zip");

        Account account = new Account();
        account.setAddress(address);
        when(mockedAccountMapper.getById(new Long(123))).thenReturn(account);

        order = new ReserveOrder();
        order.setAccount_id(new Long(123));
        order.setItem_id(new Long(123));
        order.setOrder_id(new Long(123));
        order.setStatus(OrderStatus.READY_FOR_SHIPMENT);

        paidOrder = new PaidOrder();
        paidOrder.setOrders(asList(order));

        when(mockedPaidOrderMapper.getByPaidOrderId(anyInt())).thenReturn(paidOrder);
        when(mockedReserveOrderMapper.get(new Long(123))).thenReturn(order);
    }

    @Test
    public void shouldReturnInvoice() throws Exception {
        assertEquals(reserveOrderService.getInvoice(order).getGrossTotal(), new BigDecimal(12).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    }

    @Test
    public void shouldReturnTrueWhenOrderIsReservedByAccount() throws Exception {
        when(mockedAccountMapper.getByName(anyString())).thenReturn(new Account().setAccount_id(123L));

        assertTrue(reserveOrderService.isReservedByCurrentAccount("accountName", order));
    }

    @Test
    public void shouldDeleteAnOrder() {
        ReserveOrder reserveOrder = new ReserveOrder(1L, 1L, new Date());

        reserveOrderService.delete(reserveOrder);

        verify(mockedReserveOrderMapper).delete(reserveOrder);

    }

    @Test
    public void shouldReturnFalseWhenItemOutOfStock() throws Exception {
        Long accountId = 1L;
        Long itemId = 1L;
        Item item = new Item();
        item.setQuantity(0L);
        order.setItem_id(itemId);
        List<ReserveOrder> reserveOrders = asList(order);

        when(mockedItemMapper.get(itemId)).thenReturn(item);
        when(mockedReserveOrderMapper.findAllFor(accountId)).thenReturn(reserveOrders);

        assertFalse(reserveOrderService.currentOrderAvailableForAccountId(accountId));
    }

    @Test
    public void shouldReturnTrueWhenItemInStock() throws Exception {
        Long accountId = 1L;
        Long itemId = 1L;
        Item item = new Item();
        item.setQuantity(1L);
        order.setItem_id(itemId);
        List<ReserveOrder> reserveOrders = asList(order);

        when(mockedItemMapper.get(itemId)).thenReturn(item);
        when(mockedReserveOrderMapper.findAllFor(accountId)).thenReturn(reserveOrders);

        assertTrue(reserveOrderService.currentOrderAvailableForAccountId(accountId));
    }

    @Test
    public void shouldDeleteOrdersGivenAccountId() throws Exception {
        Long accountId = 1L;
        when(mockedReserveOrderMapper.findAllFor(accountId)).thenReturn(asList(new ReserveOrder()));

        reserveOrderService.deleteOrdersForAccountId(accountId);

        verify(mockedReserveOrderMapper).delete(any(ReserveOrder.class));
    }

    @Test
    public void shouldGetItemQuantity() throws Exception {
        List<ReserveOrder> fakeReservedOrders = ImmutableList.of(new ReserveOrder().setItem_quantity(2L), new ReserveOrder().setItem_quantity(3L));

        when(mockedReserveOrderMapper.findAllFor(100L)).thenReturn(fakeReservedOrders);

        assertThat(reserveOrderService.getQuantityOfItemsInCart(100L), is(5));
    }

    @Test
    public void shouldUpdateOrder() throws Exception {
        ReserveOrder reserveOrder = new ReserveOrder();
        when(mockedReserveOrderMapper.get(order.getOrder_id())).thenReturn(reserveOrder);

        reserveOrderService.updateOrderDetails(order, 2L);

        verify(mockedReserveOrderMapper).save(reserveOrder);
        verify(sqlSession).commit();
    }

    @Test
    public void shouldUpdateOrdersStatusWithNewStatus() throws Exception {
        ReserveOrder reserveOrder = new ReserveOrder();
        order.setStatus(OrderStatus.PAID);
        when(mockedReserveOrderMapper.get(order.getOrder_id())).thenReturn(reserveOrder);

        reserveOrderService.updateOrderDetails(order, 2L);

        verify(mockedReserveOrderMapper).save(
                Matchers.<ReserveOrder>argThat(
                        HasPropertyWithValue.<ReserveOrder>hasProperty("status", is(OrderStatus.PAID))));
    }


    @Test
    public void shouldUpdateOrdersNoteWithNote() throws Exception {
        ReserveOrder reserveOrder = new ReserveOrder();
        order.setNote("test");
        when(mockedReserveOrderMapper.get(order.getOrder_id())).thenReturn(reserveOrder);

        reserveOrderService.updateOrderDetails(order, 2L);

        verify(mockedReserveOrderMapper).save(
                Matchers.<ReserveOrder>argThat(
                        HasPropertyWithValue.<ReserveOrder>hasProperty("note", is("test"))));
    }

    @Test
    public void shouldUpdateOrdersTransactionIdWithTransactionId() throws Exception {
        ReserveOrder reserveOrder = new ReserveOrder();
        order.setTransactionId("aaa1234");
        when(mockedReserveOrderMapper.get(order.getOrder_id())).thenReturn(reserveOrder);

        reserveOrderService.updateOrderDetails(order, 2L);

        verify(mockedReserveOrderMapper).save(
                Matchers.<ReserveOrder>argThat(
                        HasPropertyWithValue.<ReserveOrder>hasProperty("transactionId", is("aaa1234"))));
    }

    @Test
    public void shouldSendNotificationForReadyForDeliveryWhenItIsReadyToShip() throws Exception {
        ReserveOrder reserveOrder = new ReserveOrder();
        when(mockedReserveOrderMapper.get(order.getOrder_id())).thenReturn(reserveOrder);

        when(mockedPaidOrderMapper.getByPaidOrderId(2)).thenReturn(paidOrder);

        reserveOrderService.updateOrderDetails(order, 2L);

        verify(mockedMailService).send(eq(mockedHtmlBuilder), any(MailContentRender.class));
    }

    @Test
    public void shouldCreateReserveOrderWithQuantityOneIfThereIsNoExistingOrders() throws Exception {
        Account account = new Account().setAddress(new Address());
        Item item = new Item();
        ReserveOrder newReserveOrder =   reserveOrderService.createReserveOrder(account, item);
        when(mockedReserveOrderMapper.findAllNewForAccountAndItem(1L, 1L)).thenReturn(null);
        assertThat(newReserveOrder.getItem_quantity(), is(1L));
    }

    @Test
    public void shouldCreateReserveOrderWithSameOrderIdAndIncrementedItemQuantityOfExistingOrders() throws Exception {
        Account account = new Account().setAddress(new Address()).setAccount_id(1L);
        Item item = new Item().setItemId(1L);
        ReserveOrder existingReserveOrder = new ReserveOrder().setOrder_id(1L).setItem_quantity(1L);
        when(mockedReserveOrderMapper.findAllNewForAccountAndItem(account.getAccount_id(), item.getItemId())).thenReturn(existingReserveOrder);
        ReserveOrder newReserveOrder =   reserveOrderService.createReserveOrder(account, item);

        assertThat(newReserveOrder.getOrder_id(), is(existingReserveOrder.getOrder_id()));
        assertThat(newReserveOrder.getItem_quantity(), is(2L));
    }
}



package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.PaidOrderMapper;
import com.trailblazers.freewheelers.mappers.ReserveOrderMapper;
import com.trailblazers.freewheelers.model.PaidOrder;
import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.PaidOrderService;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class PaidOrderServiceImplTest {

    @Mock
    SqlSession sqlSession;

    @Mock
    ReserveOrderMapper reserveOrderMapper;

    @Mock
    PaidOrderMapper paidOrderMapper;

    @Mock
    ItemService mockedItemService;

    @Mock
    ReserveOrderService mockedReserveOrderService;

    PaidOrderService paidOrderService;

    @Before
    public void setUp() {
        initMocks(this);
        when(sqlSession.getMapper(ReserveOrderMapper.class)).thenReturn(reserveOrderMapper);
        when(sqlSession.getMapper(PaidOrderMapper.class)).thenReturn(paidOrderMapper);
        paidOrderService = new PaidOrderServiceImpl(sqlSession);
        paidOrderService.setItemService(mockedItemService);
        paidOrderService.setReserveOrderService(mockedReserveOrderService);
    }



    @Test
    public void shouldUpdateReserveOrdersAndPaidOrders() {

        Long accountId = 1L;
        Long itemId = 1L;
        Integer lastPayId = 1;
        ReserveOrder reserveOrder = new ReserveOrder(accountId,itemId, new Date());
        when(mockedReserveOrderService.findAllOrdersByAccountId(accountId)).thenReturn(asList(reserveOrder));
        when(paidOrderMapper.getLastPayId()).thenReturn(lastPayId);

        paidOrderService.moveItemsToPaidForAccountId(accountId);

        verify(mockedReserveOrderService).findAllOrdersByAccountId(accountId);
        verify(paidOrderMapper).savePaidOrderInfo(any(PaidOrder.class));
        verify(paidOrderMapper, atMost(1)).saveReservedItem(reserveOrder, lastPayId);
        verify(mockedReserveOrderService).delete(reserveOrder);
        verify(mockedItemService).decreaseQuantityByOneForItemId(itemId);
    }

    @Test
    public void shouldGetAllPaidOrders() throws Exception {
        List<PaidOrder> paidOrders = new ArrayList<PaidOrder>();
        when(paidOrderMapper.getAllPaidOrders()).thenReturn(paidOrders);

        paidOrderService.getAllPaidOrders();

        verify(paidOrderMapper).getAllPaidOrders();
    }


}

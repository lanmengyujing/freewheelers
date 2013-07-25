package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.OrderStatus;
import com.trailblazers.freewheelers.model.PaidOrder;
import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.service.PaidOrderService;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AdminControllerTest {

    private Model model;
    @Mock
    private PaidOrderService mockedPaidOrderService;
    @Mock
    private ReserveOrderService mockedReserveOrderService;

    private AdminController adminController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        model = new ExtendedModelMap();
        adminController = new AdminController();
        adminController.setPaidOrderService(mockedPaidOrderService);
        adminController.setReserveOrderService(mockedReserveOrderService);

    }

    @Test
    public void shouldReturnAdminProfileView() throws Exception {
        List<PaidOrder> paidOrders = asList(new PaidOrder());
        when(mockedPaidOrderService.getAllPaidOrders()).thenReturn(paidOrders);


        assertThat(adminController.get(model), is("adminProfile"));
    }

    @Test
    public void shouldModelHavePaidOrderAttribute() throws Exception {
        List<PaidOrder> paidOrders = new ArrayList<PaidOrder>();

        when(mockedPaidOrderService.getAllPaidOrders()).thenReturn(paidOrders);

        adminController.get(model);

        assertThat((List<PaidOrder>) model.asMap().get("paidOrders"), is(paidOrders));
    }

    @Test
    public void shouldUpdateOrder() throws Exception {
        ReserveOrder reserveOrder = new ReserveOrder();
        reserveOrder.setNote("note").setOrder_id(1L).setStatus(OrderStatus.PAID);

        Long paidOrderID = 2L;
        adminController.updateOrder(model, "PAID", "1", "note", paidOrderID);

        verify(mockedReserveOrderService).updateOrderDetails(reserveOrder, paidOrderID);
    }

    @Test
    public void shouldHaveAModelWithThePaidOrders() throws Exception {
        List<PaidOrder> paidOrders = new ArrayList<PaidOrder>();

        when(mockedPaidOrderService.getAllPaidOrders()).thenReturn(paidOrders);

        Long paidOrderID = 2L;
        adminController.updateOrder(model, "PAID", "1", "note", paidOrderID);

        assertThat((List<PaidOrder>) model.asMap().get("paidOrders"), is(paidOrders));
    }

    @Test
    public void shouldReturnAdminProfileViewForUpdateOrder() throws Exception {
        Long paidOrderID = 2L;
        String page = adminController.updateOrder(model, "PAID", "1", "note", paidOrderID);

        assertThat(page, is(AdminController.PAGE));
    }
}

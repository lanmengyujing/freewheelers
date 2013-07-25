package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Address;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ReserveOrder;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.impl.AccountServiceImpl;
import com.trailblazers.freewheelers.service.impl.ItemServiceImpl;
import com.trailblazers.freewheelers.service.impl.ReserveOrderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ShoppingCartControllerTest {


    private ShoppingCartController shoppingCartController;
    private String orderId;
    private Principal mockedPrincipal;
    private ReserveOrderServiceImpl reserveOrderService;
    private AccountService mockedAccountService;
    private ItemServiceImpl itemService;
    private Model model;

    @Before
    public void setUp() throws Exception {

        shoppingCartController = new ShoppingCartController();
        orderId = "123";
        mockedPrincipal = mock(Principal.class);

        reserveOrderService = mock(ReserveOrderServiceImpl.class);
        itemService = mock(ItemServiceImpl.class);

        shoppingCartController.setReserveOrderService(reserveOrderService);
        shoppingCartController.setItemService(itemService);

    }

    @Test
    public void shouldRedirectBackToShoppingCartPageAfterDeleting() throws Exception {

        assertThat(shoppingCartController.deleteItem(orderId, mockedPrincipal), is("redirect:/shoppingCart"));

    }

    @Test
    public void shouldRetrieveOrderBasedOnOrderID() throws Exception {

        shoppingCartController.setReserveOrderService(reserveOrderService);

        shoppingCartController.deleteItem(orderId, mockedPrincipal);

        verify(reserveOrderService).get(Integer.valueOf(orderId).longValue());

    }

    @Test
    public void shouldDeleteItemFromReservedOrder() throws Exception {

        ReserveOrder order = new ReserveOrder();

        when(reserveOrderService.get(Long.decode(orderId))).thenReturn(order);

        shoppingCartController.deleteItem(orderId,mockedPrincipal);

        verify(reserveOrderService).delete(order);
    }

    @Test
    public void shouldReturnTheListOfReservedOrdersForUser(){

        Long accountId = 1L;
        Account account = new Account();
        account.setAccount_id(accountId);

        ReserveOrder reserveOrder = new ReserveOrder();
        reserveOrder.setAccount_id(accountId);

        when(reserveOrderService.findAllOrdersByAccountId(accountId)).thenReturn(asList(reserveOrder));

        shoppingCartController.getReserveOrdersForAccount(account);

        verify(reserveOrderService).findAllOrdersByAccountId(accountId);

    }

    @Test
    public void shouldReturnReserveOrdersWithItems(){
        Long accountId = 1L;
        Account account = new Account();
        account.setAccount_id(accountId);

        Long itemId = 2L;
        Item item = new Item();
        item.setItemId(itemId);

        ReserveOrder reserveOrder = new ReserveOrder();
        reserveOrder.setAccount_id(accountId);
        reserveOrder.setItem_id(itemId);

        when(reserveOrderService.findAllOrdersByAccountId(accountId)).thenReturn(asList(reserveOrder));
        when(itemService.get(itemId)).thenReturn(item);

        List<ReserveOrder> reserveOrders = shoppingCartController.getReserveOrdersForAccount(account);

        assertThat(reserveOrders.get(0).getItem().getItemId(),is(itemId));

    }

    @Test
    public void shouldRedirectToUserProfileWhileReservingItemIfCountryNotFound() throws Exception {
        String userName = "UserCat";
        Account account = new Account().setAddress(new Address());

        mockedAccountService = mock(AccountServiceImpl.class);
        shoppingCartController.setAccountService(mockedAccountService);

        when(mockedPrincipal.getName()).thenReturn(userName);
        when(mockedAccountService.getAccountIdByName(userName)).thenReturn(account);
        Item item = new Item();
        ReserveOrder reserveOrder = new ReserveOrder();
        when(reserveOrderService.createReserveOrder(account, item)).thenReturn(reserveOrder);

        assertThat(shoppingCartController.addItemToCart(new ExtendedModelMap(), mockedPrincipal, item), is("reserveOrderWithNoCountrySetup"));

    }


}

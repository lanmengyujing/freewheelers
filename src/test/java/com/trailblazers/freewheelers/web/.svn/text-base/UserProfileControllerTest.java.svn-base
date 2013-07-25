package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.security.Principal;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserProfileControllerTest {

    public static final String USERNAME = "fake username";
    public static final long ACCOUNT_ID = 2L;
    public static final int CART_COUNT = 10;

    private UserProfileController userProfileController;
    @Mock
    private Principal mockedPrincipal;
    @Mock
    private AccountService mockedAccountService;
    @Mock
    private ReserveOrderService mockedReserveOrderService;
    private Model model;

    @Before
    public void setUp() {
        initMocks(this);
        userProfileController = new UserProfileController();
        userProfileController.setAccountService(mockedAccountService);
        userProfileController.setReserveOrderService(mockedReserveOrderService);

        when(mockedPrincipal.getName()).thenReturn(USERNAME);
        when(mockedAccountService.getAccountIdByName(anyString())).thenReturn(createAccount());
        when(mockedReserveOrderService.getQuantityOfItemsInCart(anyLong())).thenReturn(CART_COUNT);

        model = new ExtendedModelMap();
    }

    @Test
    public void shouldReturnUserProfileUrl() throws Exception {
        assertEquals("userProfile", userProfileController.get(USERNAME, model, mockedPrincipal));
    }

    @Test
    public void shouldGetNameFromPrincipalWhenUsernameIsNull() throws Exception {
        userProfileController.get(null, model, mockedPrincipal);

        verify(mockedPrincipal).getName();
    }

    @Test
    public void shouldGetAccountByName() throws Exception {
        userProfileController.get(USERNAME, model, mockedPrincipal);

        verify(mockedAccountService).getAccountIdByName(USERNAME);
    }

    @Test
    public void shouldFindPaidOrdersForAccountId() throws Exception {
        userProfileController.get(USERNAME, model, mockedPrincipal);

        verify(mockedReserveOrderService).findAllPaidOrdersByAccountId(ACCOUNT_ID);
    }

    @Test
    public void shouldAddItemGridToModel() throws Exception {
        userProfileController.get(USERNAME, model, mockedPrincipal);

        assertThat((ItemGrid) model.asMap().get("itemGrid"), any(ItemGrid.class));
    }

    @Test
    public void shouldAddUserDetailToModel() throws Exception {
        userProfileController.get(USERNAME, model, mockedPrincipal);

        assertEquals(createAccount(), model.asMap().get("userDetail"));
    }

    @Test
    public void shouldAddCartCountToModel() throws Exception {
        userProfileController.get(USERNAME, model, mockedPrincipal);

        assertEquals(CART_COUNT, model.asMap().get("cartCount"));
    }

    private Account createAccount() {
        return new Account().setAccount_id(ACCOUNT_ID);
    }

}

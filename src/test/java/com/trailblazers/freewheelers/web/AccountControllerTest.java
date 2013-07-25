package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Country;
import com.trailblazers.freewheelers.model.ItemType;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ServiceResult;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import sun.security.acl.PrincipalImpl;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class AccountControllerTest {
    AccountService fakeAccountService;
    HttpServletRequest fakeHttpRequest;
    AccountController accountController;
    Model fakeModel;
    ModelAndView modelAndView;
    Account fakeAccount;

    @Before
    public void setUp() {
        fakeModel = mock(Model.class);

        fakeAccount = mock(Account.class);

        fakeAccountService = mock(AccountService.class);
        fakeHttpRequest = mock(HttpServletRequest.class);
        when(fakeHttpRequest.getParameter("terms")).thenReturn("on");

        when(fakeHttpRequest.getParameter(any(String.class))).thenReturn("");
        accountController = new AccountController(fakeAccountService);
    }

    @Test
    public void testTermsAndConditionsRoute() throws Exception {
        standaloneSetup(new AccountController())
                .build()
                .perform(get("/account/terms"))
                .andExpect(status().isOk())
                .andExpect(view().name("account/termsAndConditions"));
    }

    @Test
    public void testUserInputStayWhenErrorShown() throws Exception {
        Map errors = new HashMap();
        errors.put("emailAddress", "Test error");

        ModelAndView modelAndViewWithError = accountController.showErrorsForAccount(errors, "account/create");
        assertEquals(errors, modelAndViewWithError.getModel().get("errors"));
    }

    @Test
    public void testCreateAccountForm() throws Exception {
        when(fakeAccountService.createAccount(any(Account.class))).thenReturn(getServiceResultWithErrors());
        accountController.processCreate(fakeAccount);
        verify(fakeAccountService).createAccount(any(Account.class));
    }

    @Test
    public void testCreateFormWithCountry() {
        modelAndView = accountController.createAccountForm(fakeModel);
        Map<String, Object> modelMap = modelAndView.getModel();
        assertTrue(modelMap.containsKey("country"));
    }

    @Test
    public void testCreateFormWithCountryAfterErrors() throws IOException {
        when(fakeAccountService.createAccount(any(Account.class))).thenReturn(getServiceResultWithErrors());
        AccountController accountController = new AccountController(fakeAccountService);
        modelAndView = accountController.processCreate(fakeAccount);

        Map<String, Object> modelMap = modelAndView.getModel();
        assertTrue(modelMap.containsKey("country"));
    }

    @Test
    public void testRouteCreate() throws Exception {
        standaloneSetup(new AccountController(fakeAccountService))
                .build()
                .perform(get("/account/create"))
                .andExpect(status().isOk());
    }

    @Test
    public void tesRouteCreateAccountShouldRenderSuccessView() throws Exception {
        ServiceResult<Account> fakeServiceResult = new ServiceResult<Account>(new HashMap<String, String>(), new Account());
        when(fakeAccountService.createAccount(any(Account.class))).thenReturn(fakeServiceResult);
        BCryptPasswordEncoder fakePasswordEncoder = mock(BCryptPasswordEncoder.class);
        when(fakePasswordEncoder.encode(anyString())).thenReturn("");

        accountController.setPasswordEncoding(fakePasswordEncoder);

        standaloneSetup(accountController)
                .build().perform(post("/account/create")
                .param("password", "secret"))
                .andExpect(view().name("account/createSuccess"));
    }

    @Test
    public void testCanEditUserDetails() throws Exception {
        Account account = new Account();
        ServiceResult<Account> serviceResult = new ServiceResult(new HashMap<String, String>(), account);
        when(fakeAccountService.getAccountIdByName(anyString())).thenReturn(account);
        when(fakeAccountService.editUserDetails(account)).thenReturn(serviceResult);

        Account servedAccount = new Account().setEmailAddress("t@test.com").setPhoneNumber("123-234");

        accountController.editUserDetails(servedAccount, mock(Principal.class), null);

        verify(fakeAccountService).editUserDetails(account);
        assertEquals(account.getAddress(), servedAccount.getAddress());
        assertEquals(account.getEmailAddress(), servedAccount.getEmailAddress());
        assertEquals(account.getPhoneNumber(), servedAccount.getPhoneNumber());
    }

    private ServiceResult<Account> getServiceResultWithErrors() {
        HashMap<String, String> hashMapWithError = new HashMap<String, String>();
        hashMapWithError.put("error", "any error");
        return new ServiceResult<Account>(hashMapWithError, new Account());
    }

    @Test
    public void shouldReturnEditDetailsFormWhenTheUserIsLoggedIn() throws Exception {
        Model model=new ExtendedModelMap();
        Principal principal=new PrincipalImpl("chao");
        Account account = new Account();
        when(fakeAccountService.getAccountIdByName(anyString())).thenReturn(account);

        accountController.createEditForm(model,principal) ;

        verify(fakeAccountService).getAccountIdByName(anyString());
        assertThat((Account) model.asMap().get("account"), is(account));
        assertThat((Country[]) model.asMap().get("country"), is(Country.values()));
    }

    @Test
    public void shouldRemainInEditDetailsPageWhenThereIsAnyError() throws Exception {
         Model model=new ExtendedModelMap();
        Account account = new Account();
        ServiceResult<Account> serviceResult = new ServiceResult(new HashMap<String, String>(), account);
        serviceResult.addError("some error","some error message");
        when(fakeAccountService.getAccountIdByName(anyString())).thenReturn(account);
        when(fakeAccountService.editUserDetails(account)).thenReturn(serviceResult);

        Account servedAccount = new Account().setEmailAddress("t@test.com").setPhoneNumber("123-234");

        String page=accountController.editUserDetails(servedAccount, mock(Principal.class),model);
        assertThat((Map<String, String>) model.asMap().get("errors"), is(serviceResult.getErrors()));
        assertEquals("account/editDetails",page);
    }
}

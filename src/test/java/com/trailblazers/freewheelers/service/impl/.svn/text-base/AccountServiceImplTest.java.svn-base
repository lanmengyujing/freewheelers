package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.AccountMapper;
import com.trailblazers.freewheelers.mappers.AccountRoleMapper;
import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.AccountRole;
import com.trailblazers.freewheelers.model.Address;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ServiceResult;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashMap;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class AccountServiceImplTest {

    AccountService accountService;

    @Mock
    SqlSession sqlSession;
    @Mock
    AccountMapper accountMapper;
    @Mock
    AccountRoleMapper accountRoleMapper;

    private Address address;
    private HashMap errors;
    private Account account;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(sqlSession.getMapper(AccountMapper.class)).thenReturn(accountMapper);
        when(sqlSession.getMapper(AccountRoleMapper.class)).thenReturn(accountRoleMapper);

        account = new Account();
        address = new Address();
        account.setAddress(address);

        accountService = new AccountServiceImpl(sqlSession);
        errors = new HashMap();
    }

    @Test
    public void shouldNotCreateAccountWhenThereAreValidationErrors() {
        address.setCountry("");
        account.setEmailAddress("").setPassword("").setAccount_name("").setPhoneNumber("");

        ServiceResult<Account> serviceResult = accountService.createAccount(account);

        assertTrue(serviceResult.hasErrors());
        verify(accountMapper, never()).insert(account);
        verify(accountRoleMapper, never()).insert(any(AccountRole.class));
        verify(sqlSession, never()).commit();
    }

    @Test
    public void shouldUpdateAddressWithExistingAccount() {
        address.setCountry("country");
        account.setEmailAddress("example@example.com").setPassword("example").setAccount_name("Example Person").setPhoneNumber("1234567890").setAcceptedTerms("on");

        ServiceResult<Account> serviceResult = accountService.editUserDetails(account);

        assertFalse(serviceResult.hasErrors());
        verify(accountMapper, times(1)).update(account);
        verify(sqlSession, times(1)).commit();
    }

    @Test
    public void shouldCreateAccountWhenThereAreNoValidationErrors() {
        address.setCountry("country");
        account.setEmailAddress("example@example.com").setPassword("example").setAccount_name("Example Person").setPhoneNumber("1234567890").setAcceptedTerms("on");

        ServiceResult<Account> expectedServiceResult = new ServiceResult<Account>(errors, account);

        ServiceResult<Account> serviceResult = accountService.createAccount(account);

        verify(accountMapper, times(1)).insert(account);
        verify(accountRoleMapper, times(1)).insert(any(AccountRole.class));
        verify(sqlSession, times(1)).commit();
        assertServiceResult(serviceResult, expectedServiceResult);
    }

    private void assertServiceResult(ServiceResult<Account> serviceResult, ServiceResult<Account> expectedServiceResult) {
        assertThat(serviceResult.getErrors(), is(expectedServiceResult.getErrors()));
        assertThat(serviceResult.getModel(), is(expectedServiceResult.getModel()));
    }

}

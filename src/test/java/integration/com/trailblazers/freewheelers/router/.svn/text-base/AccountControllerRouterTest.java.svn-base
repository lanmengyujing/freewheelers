package integration.com.trailblazers.freewheelers.router;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ServiceResult;
import com.trailblazers.freewheelers.web.AccountController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class AccountControllerRouterTest {
    AccountService fakeAccountService;

    @Before
    public void setUp() {
        fakeAccountService = mock(AccountService.class);
    }

    @Test
    public void testRouteCreate() throws Exception {
        standaloneSetup(new AccountController())
                .build()
                .perform(get("/account/create"))
                .andExpect(status().isOk());
    }

    @Test
    public void tesRouteCreateAccountShouldRenderSuccessView() throws Exception {
        ServiceResult<Account> fakeServiceResult = new ServiceResult<Account>(new HashMap<String, String>(), new Account());
        when(fakeAccountService.createAccount(Matchers.any(Account.class))).thenReturn(fakeServiceResult);

        standaloneSetup(new AccountController(fakeAccountService))
                .build().perform(post("/account/create").param("terms", "on"))
                .andExpect(view().name("account/createSuccess"));
    }
}
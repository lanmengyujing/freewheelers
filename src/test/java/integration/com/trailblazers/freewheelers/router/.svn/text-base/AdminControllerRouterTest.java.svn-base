package integration.com.trailblazers.freewheelers.router;

import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.PaidOrderService;
import com.trailblazers.freewheelers.service.ReserveOrderService;
import com.trailblazers.freewheelers.web.AdminController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class AdminControllerRouterTest {

    private AdminController adminController;
    @Mock
    private ItemService mockedItemService;
    @Mock
    private AccountService mockedAccountService;
    @Mock
    private ReserveOrderService mockedReserveOrderService;
    @Mock
    private PaidOrderService mockedPaidOrderService;

    @Before
    public void setUp() {
        initMocks(this);
        adminController = new AdminController();
        adminController.setItemService(mockedItemService);
        adminController.setAccountService(mockedAccountService);
        adminController.setReserveOrderService(mockedReserveOrderService);
        adminController.setPaidOrderService(mockedPaidOrderService);
    }

    @Test
    public void shouldRenderAdminView() throws Exception {

        standaloneSetup(adminController)
                .build().perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminProfile"));
    }
}



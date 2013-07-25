package integration.com.trailblazers.freewheelers.router;

import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.web.HomeController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class HomeControllerRouterTest {

    private HomeController homeController;
    @Mock
    private ItemService mockedItemService;

    @Before
    public void setUp() {
        initMocks(this);
        homeController = new HomeController();
        homeController.setItemService(mockedItemService);
    }

    @Test
    public void shouldRenderToHomeView() throws Exception {
        standaloneSetup(homeController)
                .build().perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }
}
package integration.com.trailblazers.freewheelers.router;

import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.web.ItemController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ItemControllerRouterTest {

    public static final String ITEM_DETAILS_URL = "/item/details/1";
    private ItemController itemController;
    @Mock
    private ItemService mockedItemService;

    @Mock
    private Model fakeModel;

    @Before
    public void setUp() {
        initMocks(this);
        itemController = new ItemController();
        itemController.setItemService(mockedItemService);
    }

    @Test
    public void shouldRenderToHome() throws Exception {
        standaloneSetup(itemController)
                .build().perform(get("/item"))
                .andExpect(status().isOk())
                .andExpect(view().name("/itemList"));
    }


    @Test
    public void shouldOpenModalDialogWhenWeClickMoreDetails() throws Exception {
        standaloneSetup(itemController)
                .build().perform(get(ITEM_DETAILS_URL))
                .andExpect(status().isOk())
                .andExpect(view().name("/itemDetails"));
    }

}

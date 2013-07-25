package integration.com.trailblazers.freewheelers.router;

import com.trailblazers.freewheelers.web.OrderController;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class OrderControllerRouterTest {

    @Test
    public void shouldShouldRenderOrderSummaryPage() throws Exception {
        //Given
        standaloneSetup(new OrderController())
                .build()
                .perform(get("/order/summary"))
                .andExpect(status().isOk())
                .andExpect(view().name("orderSummary"));
    }
}

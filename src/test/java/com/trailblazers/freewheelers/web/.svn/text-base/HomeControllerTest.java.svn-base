package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.service.ItemService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class HomeControllerTest {

    @Mock
    private ItemService itemService;
    @Mock
    private Model model;

    private HomeController homeController;

    @Before
    public void SetUp() {
        initMocks(this);
        homeController = new HomeController();
    }

    @Test
    public void shouldListItemsWhoseQuantityIsGreaterThanZeroOnTheHomePage() throws Exception {
        // given
        homeController.setItemService(itemService);

        ItemGrid itemGrid = new ItemGrid();
        when(itemService.getItemsWithNonZeroQuantity()).thenReturn(itemGrid);

        // when
        homeController.get(model, null);

        // then
        verify(model).addAttribute("itemGrid", itemGrid);
    }

}

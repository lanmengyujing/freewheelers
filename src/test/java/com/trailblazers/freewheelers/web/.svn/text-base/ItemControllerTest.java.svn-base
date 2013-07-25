package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ItemType;
import com.trailblazers.freewheelers.service.ImageUploadService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.ServiceResult;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ItemControllerTest {

    @Mock
    private ItemService mockedItemService;

    @Mock
    private ImageUploadService mockedImageUploadService;

    @Mock
    private Iterator<String> fileNames;

    private Model model;

    @Mock
    private ServiceResult<Item> mockedServiceResult;

    @Mock
    private MultipartHttpServletRequest mockedMultipartHttpServletRequest;

    @Mock
    private ServletContext mockedServletContext;

    private ItemController itemController;
    private ItemGrid itemGrid;

    @Before
    public void setUp() {
        initMocks(this);
        itemController = new ItemController();
        itemController.setItemService(mockedItemService);
        model = new ExtendedModelMap();
        itemGrid = new ItemGrid();
    }

    @Test
    public void shouldReturnItemDetailsForDefaultView() throws Exception {
        when(mockedItemService.findAll()).thenReturn(itemGrid);
        itemController.get(model, null);

        assertThat((ItemGrid) model.asMap().get("itemGrid"), is(itemGrid));
        assertThat((ItemType[]) model.asMap().get("itemTypes"), is(ItemType.values()));
    }

    @Test
    public void shouldSaveImage() throws Exception {
        itemController.setServletContext(mockedServletContext);
        itemController.setImageUploadService(mockedImageUploadService);
        when(mockedItemService.saveItem(any(Item.class))).thenReturn(mockedServiceResult);
        when(mockedServiceResult.hasErrors()).thenReturn(false);
        when(mockedServletContext.getRealPath("images")).thenReturn(anyString());
        Item item = new Item();
        itemController.post(model, item);

        verify(mockedImageUploadService).storeImageToDisk(isA(Item.class), anyString());
    }

    @Test
    public void shouldReturnImageInformationAfterUpload() throws Exception {
        itemController.setImageUploadService(mockedImageUploadService);
        when(mockedMultipartHttpServletRequest.getFileNames()).thenReturn(fileNames);
        when(fileNames.hasNext()).thenReturn(true);
        when(fileNames.next()).thenReturn("freewheeler");
        MultipartFile multipartFile = mock(MultipartFile.class);
        when(mockedMultipartHttpServletRequest.getFile("freewheeler")).thenReturn(multipartFile);

        itemController.uploadImage(mockedMultipartHttpServletRequest);

        verify(mockedImageUploadService).saveToTmp(multipartFile);
    }

    @Test
    public void shouldReturnItemDetailsGivenItemIdAsUrlParameter() throws Exception {
        Item item = new Item();
        itemController.setItemService(mockedItemService);
        when(mockedItemService.get(1L)).thenReturn(item);
        itemController.getDetails(1L, model) ;
        assertThat((Item)model.asMap().get("item"), is(item));
    }

    @Test
    public void shouldRedirectToItemPageAfterDelete() throws Exception {
        String redirectPage=itemController.deleteAllItemsInTheItemGrid(model, itemGrid);
        assertThat(redirectPage, is("redirect:/item"));
    }

    @Test
    public void shouldDeleteItems() throws Exception {
        itemController.deleteAllItemsInTheItemGrid(model, itemGrid);

        verify(mockedItemService).deleteItems(itemGrid);
    }

    @Test
    public void shouldUpdateAllItems() throws Exception {
        itemController.updateAllItems(model, itemGrid);

        verify(mockedItemService).saveAll(itemGrid);
    }

}

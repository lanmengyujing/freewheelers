package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ItemType;
import com.trailblazers.freewheelers.service.ImageUploadService;
import com.trailblazers.freewheelers.service.ItemService;
import com.trailblazers.freewheelers.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping(ItemController.URL)
public class ItemController {

    static final String URL = "/item";
    static final String PAGE = "/itemList";
    static final String ITEM_DETAILS_PAGE = "/itemDetails";

    @Autowired
    private ServletContext servletContext;

    private ItemService itemService;

    private ImageUploadService imageUploadService;

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model, @ModelAttribute Item item) {
        model.addAttribute("itemGrid", itemService.findAll());
        model.addAttribute("itemTypes", ItemType.values());
        return PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String post(Model model, @ModelAttribute Item item) {
        imageUploadService.storeImageToDisk(item, getImageStorePath());
        ServiceResult<Item> result = itemService.saveItem(item);
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getErrors());
            model.addAttribute("itemGrid", itemService.findAll());
            model.addAttribute("itemTypes", ItemType.values());
            return PAGE;
        }
        return "redirect:" + URL;
    }

    @RequestMapping(value = "/details/{itemId:.*}", method = RequestMethod.GET)
    public String getDetails(@PathVariable(value = "itemId") Long itemId, Model model) {
        if (itemId != null) {
            Item item = itemService.get(itemId);
            if (item != null) {
                model.addAttribute("item", item);
            }
        }

        return ITEM_DETAILS_PAGE;
    }

    private String getImageStorePath() {
        return servletContext.getRealPath("images");
    }

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, String> uploadImage(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        String fileName = fileNames.next();
        MultipartFile multipartFile = request.getFile(fileName);
        return imageUploadService.saveToTmp(multipartFile);
    }

    @RequestMapping(method = RequestMethod.POST, params = "update=Update all enabled items")
    public String updateAllItems(Model model, @ModelAttribute ItemGrid itemGrid) {
        itemService.saveAll(itemGrid);
        return "redirect:" + URL;
    }

    @RequestMapping(method = RequestMethod.POST, params = "delete=Delete all enabled items")
    public String deleteAllItemsInTheItemGrid(Model model, @ModelAttribute ItemGrid itemGrid) {
        itemService.deleteItems(itemGrid);
        return "redirect:" + URL;
    }

    @Autowired
    public void setImageUploadService(ImageUploadService imageUploadService) {
        this.imageUploadService = imageUploadService;
    }

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    protected void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

}

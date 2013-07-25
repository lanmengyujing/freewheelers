package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.ImageUploadService;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ImageUploadServiceImpl implements ImageUploadService {

    private String tmpUploadDirection;

    @Override
    public Map<String, String> saveToTmp(MultipartFile multipartFile) {
        Map<String, String> imageInfo = new HashMap<String, String>();
        imageInfo.put("name", multipartFile.getOriginalFilename());
        imageInfo.put("size", String.valueOf(multipartFile.getSize()));
        String uuidName = getUUIDName(multipartFile);
        imageInfo.put("uuidName", uuidName);
        try {
            multipartFile.transferTo(new File(getImageRealPath(uuidName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageInfo;
    }

    private String getImageRealPath(String uuidName) {
        return tmpUploadDirection + "/" + uuidName;
    }

    @Override
    public void storeImageToDisk(Item item, String imageStorePath) {
        try {
            File imageFile = new File(getImageRealPath(item.getImagePath()));
            if (!imageFile.exists())
                return;

            FileCopyUtils.copy(imageFile, new File(imageStorePath + "/items/" + item.getImagePath()));
            item.setImagePath("/images/items/" + item.getImagePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getUUIDName(MultipartFile multipartFile) {
        return UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();
    }

    public String getTmpUploadDirection() {
        return tmpUploadDirection;
    }

    public void setTmpUploadDirection(String tmpUploadDirection) {
        this.tmpUploadDirection = tmpUploadDirection;
    }
}

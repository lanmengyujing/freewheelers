package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.model.Item;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ImageUploadService {

    Map<String, String> saveToTmp(MultipartFile multipartFile);

    void storeImageToDisk(Item item, String imageStorePath);
}

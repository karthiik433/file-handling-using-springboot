package com.example.filehandling.controller;

import com.example.filehandling.exception.CustomException;
import com.example.filehandling.service.ImageDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
public class Controller {

    @Autowired
    private ImageDataService imageDataService;

    @PostMapping("/uploadImage")
    public ResponseEntity<String> saveImage(@RequestPart("image")MultipartFile imageFile,
                                            @RequestParam("name") String name,
                                            @RequestParam("address") String address,
                                            @RequestParam("number") String number) throws IOException {

        if(imageFile != null){
            log.info("image file name received "+imageFile.getName());
        }else{
            log.info("Image not received");
        }

        String response = imageDataService.saveimageData(imageFile.getBytes(),name,address,number);

        return ResponseEntity.status(201)
                .body(response);

    }

    @GetMapping("/getImage/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws CustomException {

        byte[] imageByteData = imageDataService.getImage(id);

        log.info("Fetched the image successfully ");
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageByteData);

    }
}

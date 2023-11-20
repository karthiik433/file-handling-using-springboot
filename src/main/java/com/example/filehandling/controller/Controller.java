package com.example.filehandling.controller;

import com.example.filehandling.exception.CustomException;
import com.example.filehandling.service.ImageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class Controller {

    @Autowired
    private ImageDataService imageDataService;

    @PostMapping("/uploadImage")
    public ResponseEntity<String> saveImage(@RequestPart("image")MultipartFile imageFile,
                                            @RequestParam("name") String name,
                                            @RequestParam("address") String address,
                                            @RequestParam("number") String number) throws IOException {

        if(imageFile != null){
            System.out.println("image file name received "+imageFile.getName());
        }else{
            System.out.println("Image not received");
        }

        String response = imageDataService.saveimageData(imageFile.getBytes(),name,address,number);

        return ResponseEntity.status(201)
                .body(response);

    }

    @GetMapping("/getImage")
    public ResponseEntity<byte[]> getImage() throws CustomException {

        byte[] imageByteData = imageDataService.getImage();

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageByteData);

    }
}

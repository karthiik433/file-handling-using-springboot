package com.example.filehandling.controller;

import com.example.filehandling.dto.ImagesResponseDto;
import com.example.filehandling.exception.CustomException;
import com.example.filehandling.service.ImageDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@CrossOrigin("http://localhost:3000")
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

    @GetMapping(value = "/getImage/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> getImage(@PathVariable("id") Long id) throws CustomException {

        byte[] imageByteData = imageDataService.getImage(id);

        log.info("Fetched the image successfully "+id);
        ByteArrayResource resource = new ByteArrayResource(imageByteData);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);

    }

    @GetMapping(value = "/getImages")
    public ResponseEntity<ImagesResponseDto> getImages(){

        ImagesResponseDto imagesResponseDto = imageDataService.getImageMetaData();
        log.info("Fetched the images metadata successfully");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(imagesResponseDto);
    }

}

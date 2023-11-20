package com.example.filehandling.service;

import com.example.filehandling.entity.ImageData;
import com.example.filehandling.exception.CustomException;
import com.example.filehandling.repository.ImageDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ImageDataService {

    @Autowired
    private ImageDataRepository imageDataRepository;

    public String saveimageData(byte[] imageData, String name, String address, String number){


        ImageData imageData1 = new ImageData();
        imageData1.setImageData(imageData);
        imageData1.setName(name);
        imageData1.setAddress(address);
        imageData1.setMobileNumber(number);

        imageDataRepository.save(imageData1);
        return "Saved the image data successfully";
    }

    public byte[] getImage(Long id) throws CustomException {

        Optional<ImageData> imageData = imageDataRepository.findById(id);

        if(imageData.isPresent()){
            return imageData.get().getImageData();
        }else{
            log.info("Could not find the image!! ");
            throw new CustomException("Could not find the image!!");
        }
    }
}

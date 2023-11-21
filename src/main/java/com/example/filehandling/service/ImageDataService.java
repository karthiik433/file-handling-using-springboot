package com.example.filehandling.service;

import com.example.filehandling.dto.ImageDto;
import com.example.filehandling.dto.ImagesResponseDto;
import com.example.filehandling.entity.ImageData;
import com.example.filehandling.exception.CustomException;
import com.example.filehandling.repository.ImageDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public ImagesResponseDto getImageMetaData(){

        List<ImageData> imageList = imageDataRepository.findAll();
        List<ImageDto> imageDtoList = new ArrayList<>();

        imageList.forEach((item)->{

            ImageDto imageDto = new ImageDto();
            BeanUtils.copyProperties(item,imageDto);
            imageDtoList.add(imageDto);

        });
        ImagesResponseDto imagesResponseDto = new ImagesResponseDto();
        imagesResponseDto.setImageDtoList(imageDtoList);
        return imagesResponseDto;
    }

}

package com.example.filehandling.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ImagesResponseDto {

    @JsonProperty("images")
    private List<ImageDto> imageDtoList;

}

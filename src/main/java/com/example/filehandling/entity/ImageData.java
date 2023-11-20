package com.example.filehandling.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Image_Data",schema = "myschema")
@Data
public class ImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Lob
    @Column(name = "image")
    private byte[] imageData;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String mobileNumber;


}

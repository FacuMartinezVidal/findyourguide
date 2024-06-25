package com.findyourguide.api.dto.service;

import lombok.Data;

import java.time.LocalDate;

import com.findyourguide.api.entity.Service.ServiceType;

@Data
public class UpdateServiceDTO {
    private LocalDate date;
    private ServiceType serviceType;
    private Double price;
    private int quantity;
    private String name;
    private String description;
    private String country;
    private String city;
}

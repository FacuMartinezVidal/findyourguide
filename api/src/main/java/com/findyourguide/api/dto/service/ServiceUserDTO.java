package com.findyourguide.api.dto.service;

import com.findyourguide.api.entity.Service.ServiceType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ServiceUserDTO {
    private Long id;
    private String name;
    private LocalDate date;
    private ServiceType serviceType;
    private Long price;
    private int rate;
    private int quantity;
    private String description;
    private String country;
    private String city;
}
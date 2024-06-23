package com.findyourguide.api.dto.service;

import com.findyourguide.api.entity.City;
import com.findyourguide.api.entity.Country;
import lombok.Data;

import java.time.LocalDate;


@Data
public class UpdateServiceDTO {
    private LocalDate date;
    private String serviceType;
    private Long price;
    private int quantity;
    private String name;
    private String description;
    private Country country;
    private City city;
}




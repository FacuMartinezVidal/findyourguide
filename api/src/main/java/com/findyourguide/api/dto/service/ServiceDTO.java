package com.findyourguide.api.dto.service;

import com.findyourguide.api.dto.user.UserDTO;
import com.findyourguide.api.entity.City;
import com.findyourguide.api.entity.Country;
import lombok.Data;

import java.time.LocalDate;


@Data
public class ServiceDTO {
    private UserDTO guide;
    private Long id;
    private String name;
    private LocalDate date;
    private String serviceType;
    private Long price;
    private int rate;
    private int quantity;
    private String description;
    private Country country;
    private City city;
}

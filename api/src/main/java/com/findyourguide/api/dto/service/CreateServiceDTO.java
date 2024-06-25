package com.findyourguide.api.dto.service;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

import com.findyourguide.api.entity.Service.ServiceType;

@Data
public class CreateServiceDTO {
    @NotNull(message = "must hava a date")
    private LocalDate date;

    @NotNull(message = "must hava a type")
    private ServiceType serviceType;

    @NotNull(message = "must have a price")
    private Double price;

    @NotNull(message = "must have a quantity")
    private int quantity;

    @NotNull(message = "must have a name")
    private String name;

    @NotNull(message = "must have a description")
    private String description;

    @NotNull(message = "must have a country")
    private String country;

    @NotNull(message = "must have a city")
    private String city;
}

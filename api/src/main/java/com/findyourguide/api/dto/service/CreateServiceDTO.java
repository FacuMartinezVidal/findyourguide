package com.findyourguide.api.dto.service;

import com.findyourguide.api.entity.City;
import com.findyourguide.api.entity.Country;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateServiceDTO {
    @NotNull(message = "must hava a date")
    private LocalDate date;

    @NotNull(message = "must hava a type")
    private String serviceType;

    @NotNull(message = "must have a price")
    private Long price;

    @NotNull(message = "must have a quantity")
    private int quantity;

    @NotNull(message = "must have a name")
    private String name;
    private String description;
    private Country country;
    private City city;
}

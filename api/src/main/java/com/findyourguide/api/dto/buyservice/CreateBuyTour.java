package com.findyourguide.api.dto.buyservice;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateBuyTour {
    @NotNull(message = "must hava a date")
    private LocalDate date = LocalDate.now();

    @NotNull(message = "must have a Service ID")
    private Long serviceID;

}

package com.findyourguide.api.dto.Review;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InputReview {
    @NotNull(message = "must hava a score")
    private Integer score;
    @NotNull(message = "must hava a description")
    private String description;
    @NotNull(message = "must hava a date")
    private LocalDate date;
}

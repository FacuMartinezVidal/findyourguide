package com.findyourguide.api.dto.Review;

import java.time.LocalDate;

import com.findyourguide.api.dto.user.GuideDTO;
import com.findyourguide.api.dto.user.TouristDTO;

import lombok.Data;

@Data
public class ReviewDTO {
    private Long id;
    private TouristDTO from;
    private GuideDTO to;
    private Integer score;
    private String description;
    private LocalDate date;

}

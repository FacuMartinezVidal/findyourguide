package com.findyourguide.api.dto.buyservice;

import com.findyourguide.api.entity.Service;
import com.findyourguide.api.entity.Tourist;

import lombok.Data;

@Data
public class InputBuyTour {
    private Long id;
    private Service service;
    private Tourist tourist;
}

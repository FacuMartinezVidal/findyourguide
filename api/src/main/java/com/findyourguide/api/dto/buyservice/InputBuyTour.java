package com.findyourguide.api.dto.buyservice;

import com.findyourguide.api.entity.Tourist;
import com.findyourguide.api.entity.Service.Service;

import lombok.Data;

@Data
public class InputBuyTour {
    private Long id;
    private Service service;
    private Tourist tourist;
}

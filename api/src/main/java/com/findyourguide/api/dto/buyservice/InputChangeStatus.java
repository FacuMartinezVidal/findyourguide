package com.findyourguide.api.dto.buyservice;

import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedStatus;

import lombok.Data;

@Data
public class InputChangeStatus {
    private Long id;
    private PurchasedStatus status;
}

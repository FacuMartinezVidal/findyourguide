package com.findyourguide.api.dto.buyservice;

import com.findyourguide.api.dto.service.ServiceUserDTO;
import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PurchaseUserDTO {
    private Long id;
    private LocalDate date;
    private ServiceUserDTO service;
    private PurchasedStatus status;
    private Double balancePaid;
}

package com.findyourguide.api.mapper;

import java.time.LocalDate;

import com.findyourguide.api.dto.buyservice.BuyTourDTO;
import com.findyourguide.api.entity.Tourist;
import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedService;
import com.findyourguide.api.entity.Service.Service;

public class BuyTourMapper {

    // Convertir de entidad a DTO
    public static BuyTourDTO mapToBuyTourDTO(PurchasedService purchasedService) {
        if (purchasedService == null) {
            return null;
        }
        BuyTourDTO dto = new BuyTourDTO();
        dto.setId(purchasedService.getId());
        dto.setService(ServiceMapper.mapToServiceDTO(purchasedService.getService()));
        dto.setTourist(UserMapper.mapToUserDTO(purchasedService.getTourist()));
        return dto;
    }

    public static PurchasedService mapToEntityFromCreateService(Tourist tourist, Service service) {
        PurchasedService purchasedService = new PurchasedService();
        purchasedService.setService(service);
        purchasedService.setTourist(tourist);
        purchasedService.setDate(LocalDate.now());
        return purchasedService;
    }

    public static PurchasedService mapToPurchasedServiceEntity(BuyTourDTO buyTourDTO) {
        return null;
    }
}

package com.findyourguide.api.mapper;

import java.time.LocalDate;

import com.findyourguide.api.dto.buyservice.BuyTourDTO;
import com.findyourguide.api.entity.Service;
import com.findyourguide.api.entity.Tourist;
import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedService;

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
        // if (buyTourDTO == null) {
        // return null;
        // }
        // PurchasedService purchasedService = new PurchasedService();
        // purchasedService.setId(buyTourDTO.getId());
        // // Asumiendo que tienes métodos para buscar entidades existentes por ID
        // // Estas deben ser manejadas en la capa de servicio, no aquí.
        // purchasedService.setService(ServiceMapper.mapToServiceEntity(buyTourDTO.getService(),
        // null));
        // purchasedService.setTourist((Tourist)
        // UserMapper.mapToUserEntity(buyTourDTO.getTourist(), "TOURIST"));
        return null;
    }
}

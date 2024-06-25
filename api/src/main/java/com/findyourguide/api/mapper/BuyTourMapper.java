package com.findyourguide.api.mapper;

import com.findyourguide.api.dto.buyservice.BuyTourDTO;
import com.findyourguide.api.dto.buyservice.PurchaseUserDTO;
import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedService;
import com.findyourguide.api.entity.Service.Service;
import com.findyourguide.api.entity.Tourist;

import java.time.LocalDate;

import static com.findyourguide.api.mapper.ServiceMapper.mapToServiceUserDTO;

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

    public static PurchaseUserDTO mapToPurchaseUserDTO(PurchasedService service) {
        if (service == null) {
            return null;
        }
        PurchaseUserDTO dto = new PurchaseUserDTO();
        dto.setService(mapToServiceUserDTO(service.getService()));
        dto.setId(service.getId());
        dto.setDate(service.getDate());
        //TODO fix this
//        dto.setStatus(service.getStatus());
//        dto.setState(service.getState());
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

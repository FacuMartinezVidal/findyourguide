package com.findyourguide.api.mapper;

import com.findyourguide.api.dto.service.ServiceDTO;
import com.findyourguide.api.entity.Service;
import com.findyourguide.api.util.Populate;

public class ServiceMapper {
    public static ServiceDTO toDTO(Service service) {
        if (service == null) {
            return null;
        }
        ServiceDTO dto = new ServiceDTO();
        dto.setId(service.getId());
        dto.setName(service.getName());
        dto.setDescription(service.getDescription());
        dto.setPrice(service.getPrice());
        dto.setDate(service.getDate());
        dto.setQuantity(service.getQuantity());
        dto.setDescription(service.getDescription());
        dto.setCity(service.getCity());
        dto.setCountry(service.getCountry());
        dto.setGuide(Populate.populateUserResponse(service.getGuide(), "guide"));
        return dto;
    }
}

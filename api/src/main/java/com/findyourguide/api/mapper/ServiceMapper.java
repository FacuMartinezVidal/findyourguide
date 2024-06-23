package com.findyourguide.api.mapper;

import com.findyourguide.api.dto.service.ServiceDTO;
import com.findyourguide.api.entity.Service;

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
        dto.setServiceType(service.getServiceType());
        dto.setQuantity(service.getQuantity());
        dto.setCity(service.getCity());
        dto.setCountry(service.getCountry());
        if (service.getGuide() != null) {
            dto.setGuide(GuideMapper.mapToGuideDTO(service.getGuide(), false));
        }

        return dto;
    }
}

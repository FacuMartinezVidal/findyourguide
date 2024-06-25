package com.findyourguide.api.mapper;

import com.findyourguide.api.dto.service.CreateServiceDTO;
import com.findyourguide.api.dto.service.ServiceDTO;
import com.findyourguide.api.dto.service.ServiceUserDTO;
import com.findyourguide.api.entity.Guide;
import com.findyourguide.api.entity.Service.Service;

public class ServiceMapper {
    public static ServiceDTO mapToServiceDTO(Service service) {
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

    public static ServiceUserDTO mapToServiceUserDTO(Service service) {
        if (service == null) {
            return null;
        }
        ServiceUserDTO dto = new ServiceUserDTO();
        dto.setId(service.getId());
        dto.setName(service.getName());
        dto.setDescription(service.getDescription());
        dto.setPrice(service.getPrice());
        dto.setDate(service.getDate());
        dto.setServiceType(service.getServiceType());
        dto.setQuantity(service.getQuantity());
        dto.setCity(service.getCity());
        dto.setCountry(service.getCountry());
        dto.setRate(service.getRate());
        return dto;
    }


    public static Service mapToServiceEntityFromCreateServiceDTO(CreateServiceDTO serviceDTO, Guide guide) {
        Service service = new Service();
        service.setName(serviceDTO.getName());
        service.setDate(serviceDTO.getDate());
        service.setServiceType(serviceDTO.getServiceType());
        service.setPrice(serviceDTO.getPrice());
        service.setRate(0);
        service.setQuantity(serviceDTO.getQuantity());
        service.setDescription(serviceDTO.getDescription());
        service.setCountry(serviceDTO.getCountry());
        service.setCity(serviceDTO.getCity());
        service.setGuide(guide);
        return service;
    }

    public static Service mapToServiceEntity(ServiceDTO serviceDTO, Guide guide) {
        Service service = new Service();
        service.setName(serviceDTO.getName());
        service.setDate(serviceDTO.getDate());
        service.setServiceType(serviceDTO.getServiceType());
        service.setPrice(serviceDTO.getPrice());
        service.setRate(0);
        service.setQuantity(serviceDTO.getQuantity());
        service.setDescription(serviceDTO.getDescription());
        service.setCountry(serviceDTO.getCountry());
        service.setCity(serviceDTO.getCity());
        service.setGuide(guide);
        return service;
    }
}

package com.findyourguide.api.service;

import com.findyourguide.api.dto.service.CreateServiceDTO;
import com.findyourguide.api.entity.Service;
import com.findyourguide.api.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements IServiceService {

    private final ServiceRepository serviceRepository;

    public void create(CreateServiceDTO serviceDTO) {
        Service service = new Service();
        service.setDate(serviceDTO.getDate());
        service.setServiceType(serviceDTO.getServiceType());
        service.setPrice(serviceDTO.getPrice());
        service.setRate(0);
        service.setQuantity(serviceDTO.getQuantity());
        service.setDescription(serviceDTO.getDescription());
        service.setCountry(serviceDTO.getCountry());
        service.setCity(serviceDTO.getCity());

        serviceRepository.save(service);


    }
}

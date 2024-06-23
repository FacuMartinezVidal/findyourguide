package com.findyourguide.api.service;

import com.findyourguide.api.dto.service.CreateServiceDTO;
import com.findyourguide.api.dto.service.UpdateServiceDTO;
import com.findyourguide.api.entity.Service;
import com.findyourguide.api.repository.ServiceRepository;
import com.findyourguide.api.util.Populate;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements IServiceService {

    private final ServiceRepository serviceRepository;

    public void create(CreateServiceDTO serviceDTO) {
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
        serviceRepository.save(service);
    }

    public List<Service> findAll() {
        return serviceRepository.findAll();
    }

    public Optional<Service> findById(Long id) {
        return serviceRepository.findById(id);
    }

    public void deleteById(Long id) {
        serviceRepository.deleteById(id);
    }

    public void update(Long id, UpdateServiceDTO updateServiceDTO) {
        Optional<Service> serviceOptional = serviceRepository.findById(id);
        if (serviceOptional.isPresent()) {
            Service updatedService = Populate.populateUpdateService(serviceOptional.get(), updateServiceDTO);
            System.out.println(updatedService);
            serviceRepository.save(updatedService);
        }
    }
}

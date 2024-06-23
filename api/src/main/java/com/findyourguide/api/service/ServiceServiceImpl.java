package com.findyourguide.api.service;

import com.findyourguide.api.dto.service.CreateServiceDTO;
import com.findyourguide.api.dto.service.ServiceDTO;
import com.findyourguide.api.dto.service.UpdateServiceDTO;
import com.findyourguide.api.entity.Guide;
import com.findyourguide.api.entity.Service;
import com.findyourguide.api.mapper.ServiceMapper;
import com.findyourguide.api.repository.GuideRepository;
import com.findyourguide.api.repository.ServiceRepository;
import com.findyourguide.api.util.Populate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements IServiceService {
    private final ServiceRepository serviceRepository;
    private final GuideRepository guideRepository;

    public ServiceDTO create(CreateServiceDTO serviceDTO) {
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

        Optional<Guide> guide = guideRepository
                .findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        guide.ifPresent(service::setGuide);
        serviceRepository.save(service);
        return ServiceMapper.toDTO(service);
    }

    public List<ServiceDTO> findAll() {
        return serviceRepository.findAll().stream().map(
                ServiceMapper::toDTO).collect(Collectors.toList());
    }

    public List<ServiceDTO> findAllByGuide(Long id) {
        return guideRepository.findById(id)
                .map(guide -> guide.getGuideServices().stream()
                        .map(ServiceMapper::toDTO)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    public Optional<Service> findById(Long id) {
        return serviceRepository.findById(id);
    }

    public void deleteById(Long id) {
        serviceRepository.deleteById(id);
    }

    public ServiceDTO update(Long id, UpdateServiceDTO updateServiceDTO) {
        Optional<Service> serviceOptional = serviceRepository.findById(id);
        if (serviceOptional.isPresent()) {
            Service updatedService = Populate.populateUpdateService(serviceOptional.get(), updateServiceDTO);
            System.out.println(updatedService);
            serviceRepository.save(updatedService);
            return ServiceMapper.toDTO(updatedService);
        }
        return null;
    }
}

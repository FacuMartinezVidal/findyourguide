package com.findyourguide.api.service;

import com.findyourguide.api.dto.service.CreateServiceDTO;
import com.findyourguide.api.dto.service.ServiceDTO;
import com.findyourguide.api.dto.service.UpdateServiceDTO;
import com.findyourguide.api.entity.Guide;
import com.findyourguide.api.entity.Service;
import com.findyourguide.api.error.ServiceNotFoundException;
import com.findyourguide.api.error.UserNotFoundException;
import com.findyourguide.api.mapper.ServiceMapper;
import com.findyourguide.api.repository.GuideRepository;
import com.findyourguide.api.repository.ServiceRepository;
import com.findyourguide.api.service.interfaces.IServiceService;
import com.findyourguide.api.util.Populate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements IServiceService {
    private final ServiceRepository serviceRepository;
    private final GuideRepository guideRepository;

    public ServiceDTO create(CreateServiceDTO serviceDTO) {
        Guide guide = guideRepository
                .findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new UserNotFoundException());
        Service service = ServiceMapper.mapToServiceEntityFromCreateServiceDTO(serviceDTO, guide);
        serviceRepository.save(service);
        return ServiceMapper.mapToServiceDTO(service);
    }

    public List<ServiceDTO> findAll() {
        return serviceRepository.findAll().stream().map(
                ServiceMapper::mapToServiceDTO).collect(Collectors.toList());
    }

    public List<ServiceDTO> findAllByGuide(Long id) {
        return guideRepository.findById(id)
                .map(guide -> guide.getGuideServices().stream()
                        .map(ServiceMapper::mapToServiceDTO)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new UserNotFoundException());
    }

    public ServiceDTO findById(Long id) throws ServiceNotFoundException {
        return serviceRepository.findById(id)
                .map(ServiceMapper::mapToServiceDTO)
                .orElseThrow(() -> new ServiceNotFoundException());
    }

    public void deleteById(Long id) throws ServiceNotFoundException {
        if (!serviceRepository.existsById(id)) {
            throw new ServiceNotFoundException();
        }
        serviceRepository.deleteById(id);
    }

    public ServiceDTO update(Long id, UpdateServiceDTO updateServiceDTO) {
        Optional<Service> serviceOptional = serviceRepository.findById(id);
        if (serviceOptional.isPresent()) {
            Service updatedService = Populate.populateUpdateService(serviceOptional.get(), updateServiceDTO);
            System.out.println(updatedService);
            serviceRepository.save(updatedService);
            return ServiceMapper.mapToServiceDTO(updatedService);
        } else {
            throw new ServiceNotFoundException();
        }

    }
}

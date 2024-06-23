package com.findyourguide.api.service;

import com.findyourguide.api.dto.service.CreateServiceDTO;
import com.findyourguide.api.dto.service.ServiceDTO;
import com.findyourguide.api.dto.service.UpdateServiceDTO;
import com.findyourguide.api.dto.user.GuideDTO;
import com.findyourguide.api.entity.Service;

import java.util.List;
import java.util.Optional;

public interface IServiceService {
    ServiceDTO create(CreateServiceDTO serviceDTO);

    void deleteById(Long id);

    ServiceDTO update(Long id, UpdateServiceDTO updateServiceDTO);

    Optional<Service> findById(Long id);

    List<ServiceDTO> findAllByGuide(Long id);

    List<ServiceDTO> findAll();
}

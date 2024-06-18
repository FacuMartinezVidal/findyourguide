package com.findyourguide.api.service;

import com.findyourguide.api.dto.service.CreateServiceDTO;
import com.findyourguide.api.dto.service.UpdateServiceDTO;
import com.findyourguide.api.entity.Service;

import java.util.List;
import java.util.Optional;

public interface IServiceService {
    void create(CreateServiceDTO serviceDTO);

    void deleteById(Long id);

    void update(Long id, UpdateServiceDTO updateServiceDTO);

    Optional<Service> findById(Long id);

    List<Service> findAll();
}

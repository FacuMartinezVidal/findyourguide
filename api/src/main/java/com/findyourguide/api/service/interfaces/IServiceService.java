package com.findyourguide.api.service.interfaces;

import com.findyourguide.api.dto.service.CreateServiceDTO;
import com.findyourguide.api.dto.service.ServiceDTO;
import com.findyourguide.api.dto.service.UpdateServiceDTO;
import com.findyourguide.api.error.ServiceNotFoundException;
import com.findyourguide.api.error.UserNotFoundException;

import java.util.List;

public interface IServiceService {
    ServiceDTO create(CreateServiceDTO serviceDTO) throws UserNotFoundException;

    void deleteById(Long id) throws ServiceNotFoundException;

    ServiceDTO update(Long id, UpdateServiceDTO updateServiceDTO) throws ServiceNotFoundException;

    ServiceDTO findById(Long id) throws ServiceNotFoundException;

    List<ServiceDTO> findAllByGuide(Long id) throws UserNotFoundException;

    List<ServiceDTO> findAll();
}

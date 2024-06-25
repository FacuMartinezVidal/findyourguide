package com.findyourguide.api.service.interfaces;

import com.findyourguide.api.dto.buyservice.BuyTourDTO;
import com.findyourguide.api.dto.buyservice.InputChangeStatus;
import com.findyourguide.api.dto.service.UpdateServiceDTO;
import com.findyourguide.api.error.ServiceNotFoundException;
import com.findyourguide.api.error.UserNotFoundException;

import java.util.List;

public interface IBuyTour {

    List<BuyTourDTO> findAll();

    List<BuyTourDTO> findAllByTourist(Long id) throws UserNotFoundException;

    BuyTourDTO findById(Long id) throws ServiceNotFoundException;

    BuyTourDTO create(Long serviceID) throws UserNotFoundException;

    BuyTourDTO update(Long id, UpdateServiceDTO updateServiceDTO) throws ServiceNotFoundException;

    BuyTourDTO changeStatus(InputChangeStatus inputChangeStatus) throws Exception;

    void deleteById(Long id) throws ServiceNotFoundException;

}

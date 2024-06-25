package com.findyourguide.api.service.interfaces;

import com.findyourguide.api.dto.trophy.TrophyDTO;
import com.findyourguide.api.entity.User;
import com.findyourguide.api.entity.Trophy.TrophyType;
import com.findyourguide.api.error.ServiceNotFoundException;

import java.util.List;

public interface ITrophyService {
    TrophyDTO create(TrophyType type, String description, User user);

    TrophyDTO findById(Long id);

    List<TrophyDTO> findAllByUser();

    void deleteById(Long id) throws ServiceNotFoundException;

}

package com.findyourguide.api.service.interfaces;

import com.findyourguide.api.dto.trophy.TrophyDTO;
import com.findyourguide.api.entity.Trophy.Condition;

import java.util.List;

public interface ITrophyService {
    TrophyDTO create(Condition condition);

    TrophyDTO findById(Long id);

    List<TrophyDTO> findAllByUser();

    void delete(Long id);

}

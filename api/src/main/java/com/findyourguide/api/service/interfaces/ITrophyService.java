package com.findyourguide.api.service.interfaces;

import com.findyourguide.api.dto.trophy.TrophyDTO;
import com.findyourguide.api.entity.Trophy.Condition;

public interface ITrophyService {
    TrophyDTO create(Condition condition);
}

package com.findyourguide.api.mapper;

import com.findyourguide.api.dto.trophy.TrophyDTO;
import com.findyourguide.api.entity.Trophy.Trophy;

public class TrophyMapper {
    public static TrophyDTO toDTO(Trophy trophy) {
        TrophyDTO trophyDTO = new TrophyDTO();
        trophyDTO.setId(trophy.getId());
        trophyDTO.setUser(UserMapper.mapToUserDTO(trophy.getUser()));
        trophyDTO.setDate(trophy.getDate());
        trophyDTO.setCondition(trophy.getDescription());
        return trophyDTO;
    }

    public static Trophy toEntity(TrophyDTO trophyDTO) {
        Trophy trophy = new Trophy();
        trophy.setDate(trophyDTO.getDate());
        return trophy;
    }

}

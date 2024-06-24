package com.findyourguide.api.service;

import com.findyourguide.api.dto.trophy.TrophyDTO;
import com.findyourguide.api.entity.Trophy.Trophy;
import com.findyourguide.api.entity.User;
import com.findyourguide.api.error.UserNotFoundException;
import com.findyourguide.api.mapper.TrophyMapper;
import com.findyourguide.api.repository.TrophyRepository;
import com.findyourguide.api.repository.UserRepository;
import com.findyourguide.api.service.interfaces.ITrophyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ITrophyImpl implements ITrophyService {
    private final TrophyRepository trophyRepository;
    private final UserRepository userRepository;

    public TrophyDTO create() {
        User user = userRepository
                .findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UserNotFoundException::new);
        Trophy trophy = new Trophy();
        trophy.setUser(user);
        trophy.setDate(LocalDate.now());
        trophy.setCondition("First trophy");
        trophyRepository.save(trophy);
        return TrophyMapper.toDTO(trophy);
    }

}

package com.findyourguide.api.service;

import com.findyourguide.api.dto.trophy.TrophyDTO;
import com.findyourguide.api.entity.Trophy.Condition;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ITrophyImpl implements ITrophyService {
    private final TrophyRepository trophyRepository;
    private final UserRepository userRepository;

    public TrophyDTO create(Condition condition) {
        User user = userRepository
                .findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UserNotFoundException::new);
        Trophy trophy = new Trophy();
        trophy.setUser(user);
        trophy.setDate(LocalDate.now());
        if (condition.equals(Condition.SUCCESS)) {
            trophy.setCondition("Success");
        }
        if (condition.equals(Condition.REVIEW)) {
            trophy.setCondition("Failure");
        }
        trophyRepository.save(trophy);
        return TrophyMapper.toDTO(trophy);
    }

    public TrophyDTO findById(Long id) {
        return trophyRepository.findById(id)
                .map(TrophyMapper::toDTO)
                .orElseThrow(UserNotFoundException::new);
    }

    public List<TrophyDTO> findAllByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        return trophyRepository.findByUserId(user.getId())
                .orElseThrow(UserNotFoundException::new).stream().map(TrophyMapper::toDTO).collect(Collectors.toList());
    }

    public void delete(Long id) {
        trophyRepository.deleteById(id);
    }
}

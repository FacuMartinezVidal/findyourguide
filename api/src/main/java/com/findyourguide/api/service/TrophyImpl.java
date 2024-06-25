package com.findyourguide.api.service;

import com.findyourguide.api.dto.trophy.TrophyDTO;
import com.findyourguide.api.entity.Trophy.TrophyType;
import com.findyourguide.api.entity.Trophy.Trophy;
import com.findyourguide.api.entity.User;
import com.findyourguide.api.error.ServiceNotFoundException;
import com.findyourguide.api.error.UserNotFoundException;
import com.findyourguide.api.mapper.TrophyMapper;
import com.findyourguide.api.repository.TrophyRepository;
import com.findyourguide.api.repository.UserRepository;
import com.findyourguide.api.service.interfaces.ITrophyService;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrophyImpl implements ITrophyService {
    private final TrophyRepository trophyRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public TrophyDTO create(TrophyType type, String description, User user) {
        Trophy trophy = new Trophy();
        trophy.setUser(user);
        trophy.setDate(LocalDate.now());
        trophy.setDescription(description);
        trophy.setType(type);
        trophyRepository.save(trophy);
        return TrophyMapper.toDTO(trophy);
    }

    @Override
    public TrophyDTO findById(Long id) {
        return trophyRepository.findById(id)
                .map(TrophyMapper::toDTO)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<TrophyDTO> findAllByUser() {
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UserNotFoundException::new);
        return trophyRepository.findByUserId(user.getId())
                .orElseThrow(UserNotFoundException::new).stream().map(TrophyMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!trophyRepository.existsById(id)) {
            throw new ServiceNotFoundException();
        }
        trophyRepository.deleteById(id);

    }
}

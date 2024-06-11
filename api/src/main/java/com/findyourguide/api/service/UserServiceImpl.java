package com.findyourguide.api.service;

import com.findyourguide.api.dto.AllUserDTO;
import com.findyourguide.api.dto.UserDTO;
import com.findyourguide.api.entity.Tourist;
import com.findyourguide.api.repository.GuideRepository;
import com.findyourguide.api.repository.TouristRepository;
import com.findyourguide.api.util.Populate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final GuideRepository guideRepository;
    private final TouristRepository touristRepository;

    public AllUserDTO findAll(String type) {
        return null;
    }

    public Optional<UserDTO> findById(String type, Long id) {

        if (type.equals("tourist")) {
            Optional<Tourist> optionalGuide = touristRepository.findById(id);
            return optionalGuide.map(Populate::populateUserResponse);
        }

        return Optional.empty();
    }


}



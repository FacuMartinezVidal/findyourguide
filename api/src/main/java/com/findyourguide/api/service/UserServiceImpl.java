package com.findyourguide.api.service;

import com.findyourguide.api.dto.AllUserDTO;
import com.findyourguide.api.dto.UserDTO;
import com.findyourguide.api.entity.Guide;
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
            Optional<Tourist> optionalTourist = touristRepository.findById(id);
            return optionalTourist.map(tourist -> Populate.populateUserResponse(tourist, type));
        } else if (type.equals("guide")) {
            Optional<Guide> optionalGuide = guideRepository.findById(id);
            return optionalGuide.map(guide -> Populate.populateUserResponse(guide, type));
        }
        return Optional.empty();
    }


}



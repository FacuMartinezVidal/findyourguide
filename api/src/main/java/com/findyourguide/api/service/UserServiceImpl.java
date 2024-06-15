package com.findyourguide.api.service;

import com.findyourguide.api.dto.GuideDTO;
import com.findyourguide.api.dto.UserDTO;
import com.findyourguide.api.entity.Guide;
import com.findyourguide.api.entity.Tourist;
import com.findyourguide.api.entity.User;
import com.findyourguide.api.repository.GuideRepository;
import com.findyourguide.api.repository.TouristRepository;
import com.findyourguide.api.util.Populate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final GuideRepository guideRepository;
    private final TouristRepository touristRepository;

    public List<UserDTO> findAll(String type) {
        if (type.equals("tourist")){
            return touristRepository.findAll().stream()
                    .map(t -> new UserDTO(
                            t.getId(),
                            t.getUsername(),
                            t.getFirstName(),
                            t.getLastName(),
                            t.getEmail(),
                            t.getPhone(),
                            t.getDni(),
                            t.getGender(),
                            t.getScore()
                    ))
                    .collect(Collectors.toList());

        }else if (type.equals("guide")) {
            return guideRepository.findAll().stream()
                    .map(g -> new GuideDTO(
                            g.getId(),
                            g.getUsername(),
                            g.getFirstName(),
                            g.getLastName(),
                            g.getEmail(),
                            g.getPhone(),
                            g.getDni(),
                            g.getGender(),
                            g.getScore(),
                            g.getCountry(),
                            g.getCities(),
                            g.getCredentialPhoto(),
                            g.getLanguage()
                    ))
                    .collect(Collectors.toList());
        }
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



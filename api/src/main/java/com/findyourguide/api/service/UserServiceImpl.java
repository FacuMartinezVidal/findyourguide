package com.findyourguide.api.service;

import com.findyourguide.api.dto.user.GuideDTO;
import com.findyourguide.api.dto.user.UpdateUserDTO;
import com.findyourguide.api.dto.user.UserDTO;
import com.findyourguide.api.entity.Guide;
import com.findyourguide.api.entity.Tourist;
import com.findyourguide.api.entity.User;
import com.findyourguide.api.error.UserNotFoundException;
import com.findyourguide.api.mapper.ServiceMapper;
import com.findyourguide.api.repository.GuideRepository;
import com.findyourguide.api.repository.TouristRepository;
import com.findyourguide.api.repository.UserRepository;
import com.findyourguide.api.service.interfaces.IUserService;
import com.findyourguide.api.util.Populate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final GuideRepository guideRepository;
    private final TouristRepository touristRepository;
    private final UserRepository userRepository;

    public List<UserDTO> findAll(String type) {

        switch (type.toLowerCase()) {
            case "tourist":
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
                                t.getScore()))
                        .collect(Collectors.toList());
            case "guide":
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
                                g.getLanguage(),
                                g.getGuideServices().stream()
                                        .map(ServiceMapper::mapToServiceDTO)
                                        .collect(Collectors.toList())))
                        .collect(Collectors.toList());
            default:
                return null;

        }
    }

    public Optional<UserDTO> findById(String type, Long id) throws UserNotFoundException {

        switch (type.toLowerCase()) {
            case "tourist":
                Optional<Tourist> optionalTourist = touristRepository.findById(id);
                return optionalTourist.map(tourist -> Populate.populateUserResponse(tourist, type));

            case "guide":
                Optional<Guide> optionalGuide = guideRepository.findById(id);
                return optionalGuide.map(guide -> Populate.populateUserResponse(guide, type));

            default:
                return Optional.empty();
        }

    }

    public UserDTO findByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException());
        return Populate.populateUserResponse(user, "user");
    }

    public User findByUsername(String username) throws UserNotFoundException {
        User optionalUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException());
        return optionalUser;
    }

    public void update(String type, UpdateUserDTO userDTO) {
        if (type.equals("tourist")) {
            Optional<Tourist> optionalTourist = touristRepository.findUserByUsername(userDTO.getUsername());
            if (optionalTourist.isPresent()) {
                Tourist updatedTourist = (Tourist) Populate.populateUpdateUser(optionalTourist.get(), userDTO);
                touristRepository.save(updatedTourist);
            }
        } else if (type.equals("guide")) {
            Optional<Guide> optionalGuide = guideRepository.findUserByUsername(userDTO.getUsername());
            if (optionalGuide.isPresent()) {
                Guide updatedGuide = (Guide) Populate.populateUpdateUser(optionalGuide.get(), userDTO);
                guideRepository.save(updatedGuide);
            }
        }
    }

    public void deleteById(String type, Long id) {
        if (type.equals("tourist")) {
            touristRepository.deleteById(id);
        } else if (type.equals("guide")) {
            guideRepository.deleteById(id);
        }
    }

}

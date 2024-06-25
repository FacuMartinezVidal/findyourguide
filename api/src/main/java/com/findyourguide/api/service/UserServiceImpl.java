package com.findyourguide.api.service;

import com.findyourguide.api.config.SecurityContextService;
import com.findyourguide.api.dto.user.UpdateUserDTO;
import com.findyourguide.api.dto.user.UserDTO;
import com.findyourguide.api.entity.Guide;
import com.findyourguide.api.entity.Tourist;
import com.findyourguide.api.entity.User;
import com.findyourguide.api.error.TypeNotValidException;
import com.findyourguide.api.error.UserNotFoundException;
import com.findyourguide.api.mapper.GuideMapper;
import com.findyourguide.api.mapper.TouristMapper;
import com.findyourguide.api.mapper.UserMapper;
import com.findyourguide.api.repository.GuideRepository;
import com.findyourguide.api.repository.TouristRepository;
import com.findyourguide.api.repository.UserRepository;
import com.findyourguide.api.service.interfaces.IUserService;
import com.findyourguide.api.util.Populate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final GuideRepository guideRepository;
    private final TouristRepository touristRepository;
    private final UserRepository userRepository;
    private final SecurityContextService contextService;

    public List<UserDTO> findAll() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty())
            throw new UserNotFoundException();
        return userList.stream().map(UserMapper::mapToUserDTO).collect(Collectors.toList());

    }

    public List<UserDTO> findAllByType(String role) {
        return switch (role.toLowerCase()) {
            case "tourist" -> {
                List<Tourist> touristList = touristRepository.findAll();
                yield touristList.stream().map(tourist -> TouristMapper.mapToTouristDTO(tourist, true, true)).collect(Collectors.toList());
            }
            case "guide" -> {
                List<Guide> guideList = guideRepository.findAll();
                yield guideList.stream().map(guide -> GuideMapper.mapToGuideDTO(guide, true)).collect(Collectors.toList());
            }
            default -> throw new UserNotFoundException();
        };
    }

    public UserDTO findById(String type, Long id) throws UserNotFoundException {

        return switch (type.toLowerCase()) {
            case "tourist" -> {
                yield touristRepository.findById(id)
                        .map(tourist -> TouristMapper.mapToTouristDTO(tourist, true, true))
                        .orElseThrow(UserNotFoundException::new);
            }
            case "guide" -> {
                yield guideRepository.findById(id)
                        .map(guide -> GuideMapper.mapToGuideDTO(guide, true))
                        .orElseThrow(UserNotFoundException::new);
            }
            default -> throw new TypeNotValidException(type);
        };

    }

    public UserDTO findByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return Populate.populateUserResponse(user, "user");
    }

    public UserDTO findByJwt() throws UserNotFoundException {
        String username = contextService.getAuthenticatedUser();
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return Populate.populateUserResponse(user, "user");
    }

    public User findByUsername(String username) throws UserNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    public UserDTO update(String type, UpdateUserDTO userDTO) throws TypeNotValidException, UserNotFoundException {
        return switch (type.toLowerCase()) {
            case "tourist" -> {
                Tourist tourist = touristRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                        .orElseThrow(UserNotFoundException::new);
                Tourist updatedTourist = TouristMapper.mapToTouristEntityFromUpdateTouristDTO(tourist, userDTO);
                touristRepository.save(updatedTourist);
                yield TouristMapper.mapToTouristDTO(updatedTourist, true, true);
            }
            case "guide" -> {
                Guide guide = guideRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                        .orElseThrow(UserNotFoundException::new);
                Guide updatedGuide = GuideMapper.mapToGuideEntityFromUpdateGuideDTO(guide, userDTO);
                guideRepository.save(updatedGuide);
                yield GuideMapper.mapToGuideDTO(updatedGuide, true);
            }
            default -> throw new TypeNotValidException("Unexpected value: " + type.toLowerCase());
        };
    }

    public void deleteById(String type, Long id) {
        if (type.equals("tourist")) {
            touristRepository.deleteById(id);
        } else if (type.equals("guide")) {
            guideRepository.deleteById(id);
        }
    }

}

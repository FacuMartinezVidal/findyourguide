package com.findyourguide.api.service;

import com.findyourguide.api.dto.user.LoginDTO;
import com.findyourguide.api.dto.user.RegisterDTO;
import com.findyourguide.api.dto.user.UserDTO;
import com.findyourguide.api.dto.UserLoginDTO;
import com.findyourguide.api.entity.Guide;
import com.findyourguide.api.entity.Tourist;
import com.findyourguide.api.entity.User;
import com.findyourguide.api.error.TypeNotValidException;
import com.findyourguide.api.mapper.GuideMapper;
import com.findyourguide.api.mapper.TouristMapper;
import com.findyourguide.api.repository.GuideRepository;
import com.findyourguide.api.repository.TouristRepository;
import com.findyourguide.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {

    private final JWTServiceImpl jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TouristRepository touristRepository;
    private final GuideRepository guideRepository;
    private final UserRepository userRepository;

    public UserLoginDTO login(LoginDTO request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not founded"));
        return new UserLoginDTO(user.getUsername(), jwtService.getToken(user));
    }

    public UserDTO register(RegisterDTO request) {

        switch (request.getRole().toUpperCase()) {
            case "TOURIST":
                Tourist tourist = TouristMapper.mapToTouristEntityFromCreateTouristDTO(request,
                        passwordEncoder);
                touristRepository.save(tourist);
                return TouristMapper.mapToTouristDTO(tourist, false);
            case "GUIDE":
                Guide guide = GuideMapper.mapToGuideEntityFromCreateGuideDTO(request, passwordEncoder);
                guideRepository.save(guide);
                return GuideMapper.mapToGuideDTO(guide, false);

            default:
                throw new TypeNotValidException(request.getRole());
        }
    }

}

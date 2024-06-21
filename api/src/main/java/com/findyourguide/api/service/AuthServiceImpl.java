package com.findyourguide.api.service;

import com.findyourguide.api.dto.LoginDTO;
import com.findyourguide.api.dto.RegisterDTO;
import com.findyourguide.api.dto.UserDTO;
import com.findyourguide.api.dto.UserLoginDTO;
import com.findyourguide.api.entity.Guide;
import com.findyourguide.api.entity.Role;
import com.findyourguide.api.entity.Tourist;
import com.findyourguide.api.entity.User;
import com.findyourguide.api.repository.GuideRepository;
import com.findyourguide.api.repository.TouristRepository;
import com.findyourguide.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.findyourguide.api.util.Populate.populateCommonFields;
import static com.findyourguide.api.util.Populate.populateUserResponse;

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
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new IllegalArgumentException("User not founded"));
        return new UserLoginDTO(user.getUsername(), jwtService.getToken(user));
    }

    public UserDTO register( RegisterDTO request) {
        if (request.getRole().equals("tourist")) {
            Tourist tourist = new Tourist();
            //TODO use mapToUser
            populateCommonFields(tourist, request, passwordEncoder);
            tourist.setRole(Role.TOURIST);
            touristRepository.save(tourist);
            //TODO use mapToDTO
            return populateUserResponse(tourist,request.getRole());
        }
        if (request.getRole().equals("guide")) {
            Guide guide = new Guide();
            //TODO use mapToUser
            populateCommonFields(guide, request, passwordEncoder);
            guide.setRole(Role.GUIDE);
            guide.setCredentialPhoto(request.getCredentialPhoto());
            guide.setLanguage(request.getLanguage());
            guide.setCities(request.getCities());
            guideRepository.save(guide);
            //TODO use mapToDTO
            return populateUserResponse(guide,request.getRole());
        }
        return null;
    }


}


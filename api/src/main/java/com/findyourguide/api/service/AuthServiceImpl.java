package com.findyourguide.api.service;

import com.findyourguide.api.dto.AuthResponse;
import com.findyourguide.api.dto.LoginDTO;
import com.findyourguide.api.dto.RegisterGuideDTO;
import com.findyourguide.api.dto.RegisterTouristDTO;
import com.findyourguide.api.entity.Guide;
import com.findyourguide.api.entity.Role;
import com.findyourguide.api.entity.Tourist;
import com.findyourguide.api.entity.User;
import com.findyourguide.api.repository.GuideRepository;
import com.findyourguide.api.repository.TouristRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.findyourguide.api.util.Populate.populateCommonFields;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {

    private final JWTServiceImpl jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TouristRepository touristRepository;
    private final GuideRepository guideRepository;

    public AuthResponse login(LoginDTO request, String type) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (type.equals("tourist")) {
            UserDetails user = touristRepository.findUserByUsername(request.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            String token = jwtService.getToken(user);
            return AuthResponse.builder()
                    .token(token)
                    .build();
        }
        if (type.equals("guide")) {
            UserDetails user = guideRepository.findUserByUsername(request.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            String token = jwtService.getToken(user);
            return AuthResponse.builder()
                    .token(token)
                    .build();
        }
        return null;
    }

    public AuthResponse registerTourist(RegisterTouristDTO request) {
        Tourist tourist = new Tourist();
        populateCommonFields(tourist, request, passwordEncoder);
        tourist.setRole(Role.TOURIST);
        Tourist user = touristRepository.save(tourist);
        return generateAuthResponse(user);
    }

    public AuthResponse registerGuide(RegisterGuideDTO request) {
        Guide guide = new Guide();
        populateCommonFields(guide, request, passwordEncoder);
        guide.setRole(Role.GUIDE);
        guide.setCredentialPhoto(request.getCredentialPhoto());
        guide.setLanguage(request.getLanguage());
        guide.setCities(request.getCities());
        Guide user = guideRepository.save(guide);
        return generateAuthResponse(user);
    }


    private AuthResponse generateAuthResponse(User user) {
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}


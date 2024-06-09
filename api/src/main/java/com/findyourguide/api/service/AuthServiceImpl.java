package com.findyourguide.api.service;

import com.findyourguide.api.dto.AuthResponse;
import com.findyourguide.api.dto.LoginDTO;
import com.findyourguide.api.dto.RegisterGuideDTO;
import com.findyourguide.api.dto.RegisterTouristDTO;
import com.findyourguide.api.entity.Guide;
import com.findyourguide.api.entity.Role;
import com.findyourguide.api.entity.Tourist;
import com.findyourguide.api.repository.GuideRepository;
import com.findyourguide.api.repository.TouristRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
        tourist.setUsername(request.getUsername());
        tourist.setFirstName(request.getFirstName());
        tourist.setLastName(request.getLastName());
        tourist.setEmail(request.getEmail());
        tourist.setPassword(passwordEncoder.encode(request.getPassword()));
        tourist.setPhone(request.getPhone());
        tourist.setDni(request.getDni());
        tourist.setGender(request.getGender());
        tourist.setScore(0.0);
        tourist.setRole(Role.TOURIST);
        tourist.setProfilePhoto(request.getProfilePhoto());
        tourist.setActive(true);
        Tourist user = touristRepository.save(tourist);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    public AuthResponse registerGuide(RegisterGuideDTO request) {
        Guide guide = new Guide();
        guide.setUsername(request.getUsername());
        guide.setFirstName(request.getFirstName());
        guide.setLastName(request.getLastName());
        guide.setEmail(request.getEmail());
        guide.setPassword(passwordEncoder.encode(request.getPassword()));
        guide.setPhone(request.getPhone());
        guide.setDni(request.getDni());
        guide.setGender(request.getGender());
        guide.setScore(0.0);
        guide.setRole(Role.GUIDE);
        guide.setProfilePhoto(request.getProfilePhoto());
        guide.setActive(true);
        guide.setCredentialPhoto(request.getCredentialPhoto());
        guide.setLanguage(request.getLanguage());
        guide.setCities(request.getCities());
        Guide user = guideRepository.save(guide);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}

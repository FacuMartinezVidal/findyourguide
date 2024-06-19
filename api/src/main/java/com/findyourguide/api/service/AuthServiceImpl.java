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

    public UserLoginDTO login(LoginDTO request, String type) {

        //TODO only use jwt for authentication don't use authentication manager
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (type.equals("tourist")) {
            UserDetails user = touristRepository.findUserByUsername(request.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            return new UserLoginDTO(user.getUsername(), jwtService.getToken(user));

        }
        if (type.equals("guide")) {
            UserDetails user = guideRepository.findUserByUsername(request.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            return new UserLoginDTO(
                    user.getUsername(),
                    jwtService.getToken(user)
            );

        }
        return null;
    }

    public UserDTO register(String type, RegisterDTO request) {
        if (type.equals("tourist")) {
            Tourist tourist = new Tourist();
            //TODO use mapToUser
            populateCommonFields(tourist, request, passwordEncoder);
            tourist.setRole(Role.TOURIST);
            touristRepository.save(tourist);
            //TODO use mapToDTO
            return populateUserResponse(tourist,type);
        }
        if (type.equals("guide")) {
            Guide guide = new Guide();
            //TODO use mapToUser
            populateCommonFields(guide, request, passwordEncoder);
            guide.setRole(Role.GUIDE);
            guide.setCredentialPhoto(request.getCredentialPhoto());
            guide.setLanguage(request.getLanguage());
            guide.setCities(request.getCities());
            guideRepository.save(guide);
            //TODO use mapToDTO
            return populateUserResponse(guide,type);
        }

        return null;
    }


}


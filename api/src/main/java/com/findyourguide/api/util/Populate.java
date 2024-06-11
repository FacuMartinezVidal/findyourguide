package com.findyourguide.api.util;

import com.findyourguide.api.dto.RegisterDTO;
import com.findyourguide.api.dto.UserDTO;
import com.findyourguide.api.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Populate {

    public static void populateCommonFields(User user, RegisterDTO request, PasswordEncoder passwordEncoder) {
        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setDni(request.getDni());
        user.setGender(request.getGender());
        user.setScore(0.0);
        user.setProfilePhoto(request.getProfilePhoto());
        user.setActive(true);
    }

    public static UserDTO populateUserResponse(User user) {
        UserDTO response = new UserDTO();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setFirsName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setDni(user.getDni());
        response.setGender(user.getGender());
        response.setScore(user.getScore());
        return response;
    }

}

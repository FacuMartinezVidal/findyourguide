package com.findyourguide.api.util;

import com.findyourguide.api.dto.RegisterDTO;
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
}

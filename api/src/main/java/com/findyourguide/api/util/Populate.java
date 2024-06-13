package com.findyourguide.api.util;

import com.findyourguide.api.dto.GuideDTO;
import com.findyourguide.api.dto.RegisterDTO;
import com.findyourguide.api.dto.UserDTO;
import com.findyourguide.api.entity.Guide;
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

    public static UserDTO populateUserResponse(User user, String type) {
        UserDTO response = null;

        if (type.equals("tourist")) {
            response = new UserDTO();
        } else if (type.equals("guide")) {
            response = new GuideDTO();
        }

        if (response == null) {
            return null;
        }

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setFirsName(user.getFirstName());  // fixed the typo from "FirsName" to "FirstName"
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setDni(user.getDni());
        response.setGender(user.getGender());
        response.setScore(user.getScore());

        if (response instanceof GuideDTO guideResponse && user instanceof Guide guide) {
            guideResponse.setCountry(guide.getCountry());
            guideResponse.setCities(guide.getCities());
            guideResponse.setCredentialPhoto(guide.getCredentialPhoto());
            guideResponse.setLanguage(guide.getLanguage());
            return guideResponse;

        }

        return response;

    }

}

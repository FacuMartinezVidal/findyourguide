package com.findyourguide.api.util;

import com.findyourguide.api.dto.service.UpdateServiceDTO;
import com.findyourguide.api.dto.user.GuideDTO;
import com.findyourguide.api.dto.user.RegisterDTO;
import com.findyourguide.api.dto.user.UpdateUserDTO;
import com.findyourguide.api.dto.user.UserDTO;
import com.findyourguide.api.entity.Guide;
import com.findyourguide.api.entity.User;
import com.findyourguide.api.entity.Service.Service;

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
        user.setProfilePhoto(request.getProfilePhoto());
        user.setActive(true);
    }

    public static UserDTO populateUserResponse(User user, String type) {
        UserDTO response = null;

        switch (type) {
            case "tourist", "user":
                response = new UserDTO();
                break;
            case "guide":
                response = new GuideDTO();
                break;

            default:
                return null;
        }

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setDni(user.getDni());
        response.setGender(user.getGender());

        if (response instanceof GuideDTO guideResponse && user instanceof Guide guide) {
            guideResponse.setScore(guide.getScore());
            guideResponse.setCountry(guide.getCountry());
            guideResponse.setCredentialPhoto(guide.getCredentialPhoto());
            guideResponse.setLanguage(guide.getLanguage());
            return guideResponse;

        }

        return response;

    }

    public static User populateUpdateUser(User user, UpdateUserDTO modifications) {

        if (modifications.getUsername() != null) {
            user.setUsername(modifications.getUsername());
        }
        if (modifications.getFirstName() != null) {
            user.setFirstName(modifications.getFirstName());
        }
        if (modifications.getLastName() != null) {
            user.setLastName(modifications.getLastName());
        }
        if (modifications.getEmail() != null) {
            user.setEmail(modifications.getEmail());
        }
        if (modifications.getPhone() != null) {
            user.setPhone(modifications.getPhone());
        }
        if (modifications.getDni() != null) {
            user.setDni(modifications.getDni());
        }
        if (modifications.getGender() != null) {
            user.setGender(modifications.getGender());
        }

        if (user instanceof Guide guide) {
            if (modifications.getCountry() != null) {
                guide.setCountry(modifications.getCountry());
            }
            if (modifications.getCredentialPhoto() != null) {
                guide.setCredentialPhoto(modifications.getCredentialPhoto());
            }
            if (modifications.getLanguage() != null) {
                guide.setLanguage(modifications.getLanguage());
            }
            return guide;
        }
        return user;
    }

    public static Service populateUpdateService(Service service, UpdateServiceDTO updateServiceDTO) {
        if (updateServiceDTO.getName() != null) {
            service.setName(updateServiceDTO.getName());
        }
        if (updateServiceDTO.getDate() != null) {
            service.setDate(updateServiceDTO.getDate());
        }
        if (updateServiceDTO.getServiceType() != null) {
            service.setServiceType(updateServiceDTO.getServiceType());
        }
        if (updateServiceDTO.getPrice() != null) {
            service.setPrice(updateServiceDTO.getPrice());
        }
        if (updateServiceDTO.getQuantity() != 0) {
            service.setQuantity(updateServiceDTO.getQuantity());
        }
        if (updateServiceDTO.getDescription() != null) {
            service.setDescription(updateServiceDTO.getDescription());
        }
        if (updateServiceDTO.getCountry() != null) {
            service.setCountry(updateServiceDTO.getCountry());
        }
        if (updateServiceDTO.getCity() != null) {
            service.setCity(updateServiceDTO.getCity());
        }
        return service;
    }

}

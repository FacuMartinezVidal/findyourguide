package com.findyourguide.api.mapper;

import com.findyourguide.api.dto.user.GuideDTO;
import com.findyourguide.api.dto.user.RegisterDTO;
import com.findyourguide.api.dto.user.UpdateUserDTO;
import com.findyourguide.api.entity.Guide;
import com.findyourguide.api.entity.Role;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.stream.Collectors;

public class GuideMapper {

    public static Guide mapToGuideEntityFromUpdateGuideDTO(Guide guide, UpdateUserDTO modification) {
        if (modification.getUsername() != null) {
            guide.setUsername(modification.getUsername());
        }
        if (modification.getEmail() != null) {
            guide.setEmail(modification.getEmail());
        }
        if (modification.getFirstName() != null) {
            guide.setFirstName(modification.getFirstName());
        }
        if (modification.getLastName() != null) {
            guide.setLastName(modification.getLastName());
        }
        if (modification.getPhone() != null) {
            guide.setPhone(modification.getPhone());
        }
        if (modification.getDni() != null) {
            guide.setDni(modification.getDni());
        }
        if (modification.getGender() != null) {
            guide.setGender(modification.getGender());
        }
        if (modification.getProfilePhoto() != null) {
            guide.setProfilePhoto(modification.getProfilePhoto());
        }
        if (modification.getPassword() != null) {
            guide.setPassword(modification.getPassword());
        }
        if (modification.getCountry() != null) {
            guide.setCountry(modification.getCountry());
        }
        if (modification.getCredentialPhoto() != null) {
            guide.setCredentialPhoto(modification.getCredentialPhoto());
        }
        if (modification.getLanguage() != null) {
            guide.setLanguage(modification.getLanguage());
        }
        if (modification.getScore() != null) {
            guide.setScore(modification.getScore());
        }
        return guide;
    }

    public static GuideDTO mapToGuideDTO(Guide guide, boolean includeServices) {
        if (guide == null) {
            return null;
        }
        GuideDTO guideDTO = new GuideDTO();
        guideDTO.setId(guide.getId());
        guideDTO.setUsername(guide.getUsername());
        guideDTO.setFirstName(guide.getFirstName());
        guideDTO.setLastName(guide.getLastName());
        guideDTO.setEmail(guide.getEmail());
        guideDTO.setPhone(guide.getPhone());
        guideDTO.setDni(guide.getDni());
        guideDTO.setGender(guide.getGender());
        guideDTO.setScore(guide.getScore());
        guideDTO.setCountry(guide.getCountry());
        guideDTO.setCredentialPhoto(guide.getCredentialPhoto());
        guideDTO.setLanguage(guide.getLanguage());

        if (includeServices) {
            guideDTO.setServices(guide.getGuideServices() != null ? guide.getGuideServices().stream()
                    .map(ServiceMapper::mapToServiceUserDTO)
                    .collect(Collectors.toList()) : Collections.emptyList());

        } else {
            guideDTO.setServices(Collections.emptyList());
        }

        return guideDTO;
    }

    public static Guide mapToGuideEntityFromCreateGuideDTO(RegisterDTO request,
                                                           PasswordEncoder passwordEncoder) {
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
        guide.setProfilePhoto(request.getProfilePhoto());
        guide.setActive(true);

        guide.setRole(Role.GUIDE);
        guide.setCredentialPhoto(request.getCredentialPhoto());
        guide.setLanguage(request.getLanguage());
        guide.setCountry(request.getCountry());

        return guide;
    }

    public static Guide mapToGuideEntity(GuideDTO guideDTO) {
        if (guideDTO == null) {
            return null;
        }
        Guide guide = new Guide();
        guide.setId(guideDTO.getId());
        guide.setUsername(guideDTO.getUsername());
        guide.setFirstName(guideDTO.getFirstName());
        guide.setLastName(guideDTO.getLastName());
        guide.setEmail(guideDTO.getEmail());
        guide.setPhone(guideDTO.getPhone());
        guide.setDni(guideDTO.getDni());
        guide.setGender(guideDTO.getGender());
        guide.setScore(guideDTO.getScore());
        guide.setCountry(guideDTO.getCountry());
        guide.setCountry(guideDTO.getCountry());
        guide.setCredentialPhoto(guideDTO.getCredentialPhoto());
        guide.setLanguage(guideDTO.getLanguage());
        return guide;
    }
}

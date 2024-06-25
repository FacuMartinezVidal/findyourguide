package com.findyourguide.api.mapper;

import com.findyourguide.api.dto.user.RegisterDTO;
import com.findyourguide.api.dto.user.TouristDTO;
import com.findyourguide.api.dto.user.UpdateUserDTO;
import com.findyourguide.api.entity.Role;
import com.findyourguide.api.entity.Tourist;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.stream.Collectors;

public class TouristMapper {

    public static TouristDTO mapToTouristDTO(Tourist tourist, boolean includeServices, boolean includeBalance) {
        if (tourist == null) {
            return null;
        }
        TouristDTO touristDTO = new TouristDTO();
        touristDTO.setId(tourist.getId());
        touristDTO.setUsername(tourist.getUsername());
        touristDTO.setFirstName(tourist.getFirstName());
        touristDTO.setLastName(tourist.getLastName());
        touristDTO.setEmail(tourist.getEmail());
        touristDTO.setPhone(tourist.getPhone());
        touristDTO.setDni(tourist.getDni());
        touristDTO.setGender(tourist.getGender());
        touristDTO.setBalance(tourist.getBalance());

        if (includeServices) {
            touristDTO
                    .setServicePurchased(tourist.getPurchasedService() != null ? tourist.getPurchasedService().stream()
                            .map(BuyTourMapper::mapToPurchaseUserDTO)
                            .collect(Collectors.toList()) : Collections.emptyList());
        } else {
            touristDTO.setServicePurchased(Collections.emptyList());
        }
        return touristDTO;
    }

    public static Tourist mapToTouristEntityFromCreateTouristDTO(RegisterDTO request,
            PasswordEncoder passwordEncoder) {
        Tourist tourist = new Tourist();
        tourist.setUsername(request.getUsername());
        tourist.setFirstName(request.getFirstName());
        tourist.setLastName(request.getLastName());
        tourist.setEmail(request.getEmail());
        tourist.setPassword(passwordEncoder.encode(request.getPassword()));
        tourist.setPhone(request.getPhone());
        tourist.setDni(request.getDni());
        tourist.setGender(request.getGender());
        tourist.setProfilePhoto(request.getProfilePhoto());
        tourist.setActive(true);

        tourist.setBalance(100L);
        tourist.setRole(Role.TOURIST);
        return tourist;
    }

    public static Tourist mapToTouristEntityFromUpdateTouristDTO(Tourist tourist, UpdateUserDTO modification) {

        if (modification.getUsername() != null) {
            tourist.setUsername(modification.getUsername());
        }
        if (modification.getEmail() != null) {
            tourist.setEmail(modification.getEmail());
        }
        if (modification.getFirstName() != null) {
            tourist.setFirstName(modification.getFirstName());
        }
        if (modification.getLastName() != null) {
            tourist.setLastName(modification.getLastName());
        }
        if (modification.getPhone() != null) {
            tourist.setPhone(modification.getPhone());
        }
        if (modification.getDni() != null) {
            tourist.setDni(modification.getDni());
        }
        if (modification.getGender() != null) {
            tourist.setGender(modification.getGender());
        }
        if (modification.getProfilePhoto() != null) {
            tourist.setProfilePhoto(modification.getProfilePhoto());
        }
        if (modification.getPassword() != null) {
            tourist.setPassword(modification.getPassword());
        }

        return tourist;
    }

    // public static Guide mapToTouristEntity(GuideDTO guideDTO) {
    // if (guideDTO == null) {
    // return null;
    // }
    // Guide guide = new Guide();
    // guide.setId(guideDTO.getId());
    // guide.setUsername(guideDTO.getUsername());
    // guide.setFirstName(guideDTO.getFirstName());
    // guide.setLastName(guideDTO.getLastName());
    // guide.setEmail(guideDTO.getEmail());
    // guide.setPhone(guideDTO.getPhone());
    // guide.setDni(guideDTO.getDni());
    // guide.setGender(guideDTO.getGender());
    // guide.setScore(guideDTO.getScore());
    // guide.setCountry(guideDTO.getCountry());
    // guide.setCities(guideDTO.getCities());
    // guide.setCredentialPhoto(guideDTO.getCredentialPhoto());
    // guide.setLanguage(guideDTO.getLanguage());
    // return guide;
    // }
}

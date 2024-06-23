package com.findyourguide.api.mapper;

import java.util.Collections;
import java.util.stream.Collectors;

import com.findyourguide.api.dto.user.GuideDTO;
import com.findyourguide.api.entity.Guide;

public class GuideMapper {

    public static GuideDTO mapToGuideDTO(Guide guide, boolean includeServices) {
        if (guide == null) {
            return null;
        }
        GuideDTO guideDTO = new GuideDTO();
        guideDTO.setId(guide.getId());
        guideDTO.setUsername(guide.getUsername());
        guideDTO.setFirsName(guide.getFirstName());
        guideDTO.setLastName(guide.getLastName());
        guideDTO.setEmail(guide.getEmail());
        guideDTO.setPhone(guide.getPhone());
        guideDTO.setDni(guide.getDni());
        guideDTO.setGender(guide.getGender());
        guideDTO.setScore(guide.getScore());
        guideDTO.setCountry(guide.getCountry());
        guideDTO.setCities(guide.getCities());
        guideDTO.setCredentialPhoto(guide.getCredentialPhoto());
        guideDTO.setLanguage(guide.getLanguage());

        if (includeServices) {
            guideDTO.setServices(guide.getGuideServices().stream()
                    .map(ServiceMapper::mapToServiceDTO)
                    .collect(Collectors.toList()));
        } else {
            guideDTO.setServices(Collections.emptyList());
        }

        return guideDTO;
    }

    public static Guide mapToGuideEntity(GuideDTO guideDTO) {
        if (guideDTO == null) {
            return null;
        }
        Guide guide = new Guide();
        guide.setId(guideDTO.getId());
        guide.setUsername(guideDTO.getUsername());
        guide.setFirstName(guideDTO.getFirsName());
        guide.setLastName(guideDTO.getLastName());
        guide.setEmail(guideDTO.getEmail());
        guide.setPhone(guideDTO.getPhone());
        guide.setDni(guideDTO.getDni());
        guide.setGender(guideDTO.getGender());
        guide.setScore(guideDTO.getScore());
        guide.setCountry(guideDTO.getCountry());
        guide.setCities(guideDTO.getCities());
        guide.setCredentialPhoto(guideDTO.getCredentialPhoto());
        guide.setLanguage(guideDTO.getLanguage());
        return guide;
    }
}

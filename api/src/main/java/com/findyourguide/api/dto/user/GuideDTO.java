package com.findyourguide.api.dto.user;

import com.findyourguide.api.dto.service.ServiceUserDTO;
import com.findyourguide.api.entity.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GuideDTO extends UserDTO {
    String country;
    String credentialPhoto;
    Language language;
    List<ServiceUserDTO> services;

    public GuideDTO(Long id, String username, String firstName, String lastName, String email, String phone, String dni,
                    String gender, Double score, String country, String credentialPhoto, Language language,
                    List<ServiceUserDTO> services) {
        super(id, username, firstName, lastName, email, phone, dni, gender, score);
        this.country = country;
        this.credentialPhoto = credentialPhoto;
        this.language = language;
        this.services = services;
    }
}

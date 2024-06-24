package com.findyourguide.api.dto.user;

import java.util.List;
import com.findyourguide.api.dto.service.ServiceDTO;
import com.findyourguide.api.entity.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GuideDTO extends UserDTO {
    String country;
    String credentialPhoto;
    Language language;
    List<ServiceDTO> services;

    public GuideDTO(Long id, String username, String firstName, String lastName, String email, String phone, String dni,
            String gender, Double score, String country, String credentialPhoto, Language language,
            List<ServiceDTO> services) {
        super(id, username, firstName, lastName, email, phone, dni, gender, score);
        this.country = country;
        this.credentialPhoto = credentialPhoto;
        this.language = language;
        this.services = services;
    }
}

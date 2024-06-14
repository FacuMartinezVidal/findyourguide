package com.findyourguide.api.dto;

import com.findyourguide.api.entity.Country;
import com.findyourguide.api.entity.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GuideDTO extends UserDTO{
    Country country;
    String cities;
    String credentialPhoto;
    Language language;

    public GuideDTO(Long id, String username, String firstName, String lastName, String email, String phone, String dni, String gender, Double score, Country country, String cities, String credentialPhoto) {
    }

}

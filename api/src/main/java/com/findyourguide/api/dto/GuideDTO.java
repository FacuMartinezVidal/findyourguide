package com.findyourguide.api.dto;

import com.findyourguide.api.entity.Country;
import com.findyourguide.api.entity.Language;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuideDTO extends UserDTO{
    Country country;
    String cities;
    String credentialPhoto;
    Language language;
}

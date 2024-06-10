package com.findyourguide.api.dto;


import com.findyourguide.api.entity.Language;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterGuideDTO extends RegisterDTO {
    String cities;
    String credentialPhoto;
    Language language;


}

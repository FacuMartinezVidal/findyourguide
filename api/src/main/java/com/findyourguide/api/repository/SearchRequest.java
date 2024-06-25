package com.findyourguide.api.repository;

import com.findyourguide.api.entity.Language;
import com.findyourguide.api.entity.Service.ServiceType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchRequest {
    private String firstName;
    private String lastName;
    private Language language;
    private ServiceType serviceType;
    private String city;
    private String country;
    private Double score;
}

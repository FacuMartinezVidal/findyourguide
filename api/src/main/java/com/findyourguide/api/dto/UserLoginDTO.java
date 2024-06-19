package com.findyourguide.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//TODO send maybe more data, check with client
public class UserLoginDTO {
    private String username;
    private String jwt;
}

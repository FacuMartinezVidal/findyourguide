package com.findyourguide.api.dto.buyservice;

import com.findyourguide.api.dto.service.ServiceDTO;
import com.findyourguide.api.dto.user.UserDTO;

import lombok.Data;

@Data
public class BuyTourDTO {
    private Long id;
    private ServiceDTO service;
    private UserDTO tourist;
}

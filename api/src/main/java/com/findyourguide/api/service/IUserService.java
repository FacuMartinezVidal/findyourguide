package com.findyourguide.api.service;

import com.findyourguide.api.dto.AllUserDTO;
import com.findyourguide.api.dto.UserDTO;

import java.util.Optional;

public interface IUserService {
    AllUserDTO findAll(String type);

    Optional<UserDTO> findById(String type, Long id);
}

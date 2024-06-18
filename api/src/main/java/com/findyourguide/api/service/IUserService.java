package com.findyourguide.api.service;

import com.findyourguide.api.dto.user.UpdateUserDTO;
import com.findyourguide.api.dto.user.UserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserDTO> findAll(String type);

    Optional<UserDTO> findById(String type, Long id);

    void update(String type, UpdateUserDTO userDTO);

    void deleteById(String type, Long id);
}

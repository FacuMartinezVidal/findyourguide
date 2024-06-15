package com.findyourguide.api.service;

import com.findyourguide.api.dto.UserDTO;
import com.findyourguide.api.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserDTO> findAll(String type);

    Optional<UserDTO> findById(String type, Long id);

    void deleteById(String type, Long id);
}

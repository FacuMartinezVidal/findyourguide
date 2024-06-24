package com.findyourguide.api.service.interfaces;

import com.findyourguide.api.dto.user.UpdateUserDTO;
import com.findyourguide.api.dto.user.UserDTO;
import com.findyourguide.api.entity.User;
import com.findyourguide.api.error.UserNotFoundException;
import com.findyourguide.api.error.TypeNotValidException;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserDTO> findAll(String type);

    Optional<UserDTO> findById(String type, Long id) throws UserNotFoundException, TypeNotValidException;

    void update(String type, UpdateUserDTO userDTO);

    void deleteById(String type, Long id);

    UserDTO findByEmail(String email) throws UserNotFoundException;

    UserDTO findByJwt() throws UserNotFoundException;

    User findByUsername(String username) throws UserNotFoundException;
}

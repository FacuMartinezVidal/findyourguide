package com.findyourguide.api.mapper;

import com.findyourguide.api.dto.user.UserDTO;
import com.findyourguide.api.entity.Guide;
import com.findyourguide.api.entity.Tourist;
import com.findyourguide.api.entity.User;

public class UserMapper {

    public static UserDTO mapToUserDTO(User user) {
        if (user == null)
            return null;
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setDni(user.getDni());
        userDTO.setGender(user.getGender());
        return userDTO;
    }

    public static User mapToUserEntity(UserDTO userDTO, String type) {
        if (userDTO == null)
            return null;
        User user;
        switch (type.toUpperCase()) {
            case "GUIDE":
                user = new Guide();
                break;
            case "TOURIST":
                user = new Tourist();
                break;
            default:
                return null; // or throw an exception if type is mandatory
        }
        // user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setDni(userDTO.getDni());
        user.setGender(userDTO.getGender());
        return user;
    }
}

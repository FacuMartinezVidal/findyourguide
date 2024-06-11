package com.findyourguide.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AllUserDTO {

    private List<UserDTO> users;

}

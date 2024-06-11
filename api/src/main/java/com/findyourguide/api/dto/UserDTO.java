package com.findyourguide.api.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String firsName;
    private String lastName;
    private String email;
    private String phone;
    private String dni;
    private String gender;
    private Double score;
}

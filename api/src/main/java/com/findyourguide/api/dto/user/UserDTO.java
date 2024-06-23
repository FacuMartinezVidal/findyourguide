package com.findyourguide.api.dto.user;


import com.findyourguide.api.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
/*
    public User mapToUserDTO() {
        return new User();
    }

 */
}

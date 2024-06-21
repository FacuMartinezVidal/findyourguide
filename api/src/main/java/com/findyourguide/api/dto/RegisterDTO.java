package com.findyourguide.api.dto;


import com.findyourguide.api.entity.Language;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public  class RegisterDTO {

    @NotEmpty(message = "email cannot be empty")
    String username;

    @NotEmpty(message = "first_name cannot be empty")
    String firstName;

    @NotEmpty(message = "last_name cannot be empty")
    String lastName;


    @Email(message = "must be an email")
    @NotEmpty(message = "email cannot be empty")
    String email;

    @NotEmpty(message = "password cannot be empty")
    String password;

    @NotEmpty(message = "phone cannot be empty")
    String phone;

    @NotEmpty(message = "dni cannot be empty")
    String dni;

    @NotEmpty(message = "role cannot be empty")
    String role;

    String gender;


    String profilePhoto;

    String cities;
    String credentialPhoto;
    Language language;
}


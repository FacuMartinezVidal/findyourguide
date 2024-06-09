package com.findyourguide.api.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterTouristDTO {

    @NotEmpty(message = "username cannot be empty")
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

    @NotEmpty(message = "gender cannot be empty")
    String gender;

    @NotEmpty(message = "profile_photo cannot be empty")
    String profilePhoto;
}


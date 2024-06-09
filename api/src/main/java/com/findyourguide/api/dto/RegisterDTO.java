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
public class RegisterDTO {

    @NotEmpty(message = "username cannot be empty")
    String username;

    @Email(message = "must be an email")
    @NotEmpty(message = "email cannot be empty")
    String email;

    @NotEmpty(message = "password cannot be empty")
    String password;

}


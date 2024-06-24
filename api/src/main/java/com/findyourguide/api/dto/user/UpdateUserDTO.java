package com.findyourguide.api.dto.user;

import com.findyourguide.api.entity.Language;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO {
    @NotEmpty(message = "must have an email")
    private String username;
    @Email(message = "must be an email")
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private String dni;
    private String gender;
    private String country;
    private String cities;
    private String credentialPhoto;
    private Language language;
}

package com.findyourguide.api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TouristDTO extends UserDTO {
    Long balance;

    public TouristDTO(Long id, String username, String firstName, String lastName, String email, String phone,
            String dni,
            String gender, Double score, Long balance) {
        super(id, username, firstName, lastName, email, phone, dni, gender, score);

        this.balance = balance;
    }

}
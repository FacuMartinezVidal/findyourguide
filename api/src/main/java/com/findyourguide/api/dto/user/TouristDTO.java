package com.findyourguide.api.dto.user;

import com.findyourguide.api.dto.buyservice.PurchaseUserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TouristDTO extends UserDTO {
    double balance;
    List<PurchaseUserDTO> servicePurchased;

    public TouristDTO(Long id, String username, String firstName, String lastName, String email, String phone,
            String dni,
            String gender, Double score, double balance) {
        super(id, username, firstName, lastName, email, phone, dni, gender, score);

        this.balance = balance;
    }

}

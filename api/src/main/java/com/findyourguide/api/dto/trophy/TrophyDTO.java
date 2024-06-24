package com.findyourguide.api.dto.trophy;


import com.findyourguide.api.dto.user.UserDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TrophyDTO {
    private Long id;
    private UserDTO user;
    private String condition;
    private LocalDate date;
}

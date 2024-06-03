package com.findyourguide.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ResponseDTO {

    private String message;
    private HttpStatus status;


}

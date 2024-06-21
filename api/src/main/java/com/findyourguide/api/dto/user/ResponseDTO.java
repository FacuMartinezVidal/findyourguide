package com.findyourguide.api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public  class ResponseDTO <T> {
    private HttpStatus statusCode;
    private String message;
    private T data;
}

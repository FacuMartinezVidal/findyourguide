package com.findyourguide.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties({ "trace", "cause" })
public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException() {
        super("El usuario o contraseña son incorrectos.");
    }

}

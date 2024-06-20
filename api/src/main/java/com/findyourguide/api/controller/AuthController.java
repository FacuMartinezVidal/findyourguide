package com.findyourguide.api.controller;


import com.findyourguide.api.dto.*;
import com.findyourguide.api.service.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Validated
public class AuthController {
    //TODO improve body dto
    //TODO use try and catch
    //TODO handler errors

    //TODO use interface dependency
    final AuthServiceImpl authService;

    @PostMapping("/login/{type}")

    public ResponseEntity<ResponseDTO<String>> login(@PathVariable String type, @Valid @RequestBody LoginDTO request) {
        HttpHeaders headers = new HttpHeaders();
        UserLoginDTO user = authService.login(request, type);

        headers.setBearerAuth(user.getJwt());
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new ResponseDTO<String>(HttpStatus.OK, "Login Successfully", user.getUsername()));
    }


    @PostMapping("/register/{type}")
    public ResponseEntity<ResponseDTO<UserDTO>> register( @PathVariable String type, @Valid @RequestBody RegisterDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<UserDTO>(HttpStatus.CREATED, "Register Successfully", authService.register(type, request)));
    }

}

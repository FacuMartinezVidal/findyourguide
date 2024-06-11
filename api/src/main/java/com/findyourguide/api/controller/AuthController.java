package com.findyourguide.api.controller;


import com.findyourguide.api.dto.LoginDTO;
import com.findyourguide.api.dto.RegisterDTO;
import com.findyourguide.api.dto.ResponseDTO;
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

    final AuthServiceImpl authService;

    @PostMapping("/login/{type}")
        public ResponseEntity<String> login(@PathVariable String type, @Valid @RequestBody LoginDTO request) {
        HttpHeaders headers = new HttpHeaders();

        headers.setBearerAuth(authService.login(request, type));
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body("Successfully Register");

    }

    @PostMapping("/register/{type}")
    public ResponseEntity<String> register( @PathVariable String type, @Valid @RequestBody RegisterDTO request) {
        authService.registerTourist(type, request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully registered");
    }

}

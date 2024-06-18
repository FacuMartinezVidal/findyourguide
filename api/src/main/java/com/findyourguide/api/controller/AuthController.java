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

    final AuthServiceImpl authService;

    @PostMapping("/login/{type}")
        public ResponseEntity<Map<String,String>> login(@PathVariable String type, @Valid @RequestBody LoginDTO request) {
        HttpHeaders headers = new HttpHeaders();
        UserLoginDTO user = authService.login(request, type);
        headers.setBearerAuth(user.getJwt());
        Map<String,String> response = new HashMap<>();
        response.put("username", user.getUsername());
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);

    }

    @PostMapping("/register/{type}")
    public ResponseEntity<String> register( @PathVariable String type, @Valid @RequestBody RegisterDTO request) {
        authService.register(type, request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully registered");
    }

}

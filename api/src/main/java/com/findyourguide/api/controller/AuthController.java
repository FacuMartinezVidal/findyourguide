package com.findyourguide.api.controller;


import com.findyourguide.api.dto.LoginDTO;
import com.findyourguide.api.dto.RegisterGuideDTO;
import com.findyourguide.api.dto.RegisterTouristDTO;
import com.findyourguide.api.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    final AuthServiceImpl authService;

    @PostMapping("/login/{type}")
    public ResponseEntity<String> login(@PathVariable String type, @RequestBody LoginDTO request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authService.login(request, type));
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body("Successfully logged in");

    }

    @PostMapping("/register/tourist")
    public ResponseEntity<String> register(@RequestBody RegisterTouristDTO request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authService.registerTourist(request));
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body("Successfully registered");
    }

    @PostMapping("/register/guide")
    public ResponseEntity<String> registerGuide(@RequestBody RegisterGuideDTO request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authService.registerGuide(request));
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body("Successfully registered");
    }
}

package com.findyourguide.api.controller;


import com.findyourguide.api.dto.AuthResponse;
import com.findyourguide.api.dto.LoginDTO;
import com.findyourguide.api.dto.RegisterTouristDTO;
import com.findyourguide.api.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    final AuthServiceImpl authService;

    @PostMapping("/login/{type}")
    public ResponseEntity<AuthResponse> login(@PathVariable String type, @RequestBody LoginDTO request) {
        return ResponseEntity.ok(authService.login(request, type));
    }

    @PostMapping("/register/tourist")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterTouristDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }
}

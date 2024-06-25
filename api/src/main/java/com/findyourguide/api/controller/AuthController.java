package com.findyourguide.api.controller;

import com.findyourguide.api.dto.UserLoginDTO;
import com.findyourguide.api.dto.user.LoginDTO;
import com.findyourguide.api.dto.user.RegisterDTO;
import com.findyourguide.api.dto.user.ResponseDTO;
import com.findyourguide.api.dto.user.UserDTO;
import com.findyourguide.api.service.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Validated
public class AuthController {
    // TODO improve body dto
    // TODO use interface dependency
    final AuthServiceImpl authService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<String>> login(@Valid @RequestBody LoginDTO request) {
        HttpHeaders headers = new HttpHeaders();
        UserLoginDTO user = authService.login(request);

        headers.setBearerAuth(user.getJwt());
        return ResponseEntity.status(HttpStatus.OK).headers(headers)
                .body(new ResponseDTO<String>(HttpStatus.OK, "Login Successfully", user.getEmail()));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<UserDTO>> register(@Valid @RequestBody RegisterDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDTO<UserDTO>(HttpStatus.CREATED, "Register Successfully",
                        authService.register(request)));
    }

}

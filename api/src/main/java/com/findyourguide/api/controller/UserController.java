package com.findyourguide.api.controller;

import com.findyourguide.api.dto.UserDTO;
import com.findyourguide.api.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("/user")
    public ResponseEntity<String> findAll() {
        return ResponseEntity.ok().body("all users");
    }

    @GetMapping("/user/{type}/{id}")
    public ResponseEntity<Optional<UserDTO>> findById(@PathVariable String type, @PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(type, id));
    }

}

package com.findyourguide.api.controller;

import com.findyourguide.api.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UsersController {

    @GetMapping
    public ResponseEntity<ResponseDTO> getUsers() {
        return ResponseEntity.ok().body(new ResponseDTO("welcome", HttpStatus.OK));
    }
}

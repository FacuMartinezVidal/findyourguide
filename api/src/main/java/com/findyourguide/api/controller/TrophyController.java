package com.findyourguide.api.controller;

import com.findyourguide.api.dto.trophy.TrophyDTO;
import com.findyourguide.api.dto.user.ResponseDTO;
import com.findyourguide.api.service.interfaces.ITrophyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
@Validated
@RequiredArgsConstructor
public class TrophyController {
    private final ITrophyService trophyService;

    @GetMapping("/trophies/user/{id}")
    public ResponseEntity<String> findAllByUser(@PathVariable Long id) {
        return ResponseEntity.ok("All Trophies");
    }

    @PostMapping("/trophies/")
    public ResponseEntity<ResponseDTO<TrophyDTO>> create() {
        TrophyDTO createdTrophy = trophyService.create();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTrophy.getId())
                .toUri();
        return ResponseEntity.created(location).body(new ResponseDTO<>(HttpStatus.CREATED, "Created", createdTrophy));
    }
}

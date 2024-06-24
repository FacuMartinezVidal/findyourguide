package com.findyourguide.api.controller;

import com.findyourguide.api.dto.trophy.TrophyDTO;
import com.findyourguide.api.dto.user.ResponseDTO;
import com.findyourguide.api.entity.Trophy.Condition;
import com.findyourguide.api.service.interfaces.ITrophyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
@RequiredArgsConstructor
public class TrophyController {
    private final ITrophyService trophyService;

    @GetMapping("/trophies/user")
    public ResponseEntity<ResponseDTO<List<TrophyDTO>>> findAllByUser() {
        return ResponseEntity.ok().body(new ResponseDTO<>(HttpStatus.OK, "All Trophies", trophyService.findAllByUser()));
    }

    @GetMapping("/trophies/{id}")
    public ResponseEntity<ResponseDTO<TrophyDTO>> findById(@PathVariable Long id) {
        TrophyDTO trophyDTO = trophyService.findById(id);
        return ResponseEntity.ok().body(new ResponseDTO<>(HttpStatus.OK, "Trophy Found", trophyDTO));
    }

    @PostMapping("/trophies")
    public ResponseEntity<ResponseDTO<TrophyDTO>> create() {
        TrophyDTO createdTrophy = trophyService.create(Condition.SUCCESS);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTrophy.getId())
                .toUri();
        return ResponseEntity.created(location).body(new ResponseDTO<>(HttpStatus.CREATED, "Created", createdTrophy));
    }

}

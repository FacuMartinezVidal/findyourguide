package com.findyourguide.api.controller;

import com.findyourguide.api.dto.buyservice.BuyTourDTO;
import com.findyourguide.api.dto.buyservice.InputChangeStatus;
import com.findyourguide.api.dto.buyservice.PurchaseUserDTO;
import com.findyourguide.api.dto.service.UpdateServiceDTO;
import com.findyourguide.api.dto.user.ResponseDTO;
import com.findyourguide.api.entity.Role;
import com.findyourguide.api.service.interfaces.IBuyTour;
import com.findyourguide.api.util.UserValidations;

import jakarta.validation.Valid;
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
public class BuyTourController {
    private final IBuyTour buyService;
    private final UserValidations userValidations;

    @GetMapping("/buys")
    public ResponseEntity<ResponseDTO<List<BuyTourDTO>>> findAll() {
        userValidations.validateRole(Role.TOURIST);
        return ResponseEntity.ok().body(new ResponseDTO<>(HttpStatus.OK, "All Services", buyService.findAll()));
    }

    @GetMapping("/buys/tourist/{id}")
    public ResponseEntity<ResponseDTO<List<PurchaseUserDTO>>> findAllByTourist(@PathVariable Long id) {
        userValidations.validateRole(Role.TOURIST);
        return ResponseEntity.ok()
                .body(new ResponseDTO<>(HttpStatus.OK, "All Services", buyService.findAllByTourist(id)));
    }

    @GetMapping("/buys/{id}")
    public ResponseEntity<ResponseDTO<BuyTourDTO>> findById(@PathVariable Long id) {
        userValidations.validateRole(Role.TOURIST);
        BuyTourDTO serviceDTO = buyService.findById(id);
        return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK, "Service Found", serviceDTO));
    }

    @PostMapping("/buys/{id}")
    public ResponseEntity<ResponseDTO<BuyTourDTO>> create(@PathVariable Long id) {
        userValidations.validateRole(Role.TOURIST);
        BuyTourDTO createdService = buyService.create(id);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdService.getId())
                .toUri();
        return ResponseEntity.created(location)
                .body(new ResponseDTO<>(HttpStatus.CREATED, "Created", createdService));
    }

    @DeleteMapping("/buys/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteById(@PathVariable Long id) {
        userValidations.validateRole(Role.TOURIST);
        buyService.deleteById(id);
        return ResponseEntity.ok().body(new ResponseDTO<>(HttpStatus.OK, "Successfully Deleted", null));
    }

    @PutMapping("buys/status")
    public ResponseEntity<ResponseDTO<BuyTourDTO>> changeStatus(
            @Valid @RequestBody InputChangeStatus inputChangeStatus) throws Exception {
        return ResponseEntity.ok().body(
                new ResponseDTO<>(HttpStatus.OK, "Successfully Updated",
                        buyService.changeStatus(inputChangeStatus)));
    }

}

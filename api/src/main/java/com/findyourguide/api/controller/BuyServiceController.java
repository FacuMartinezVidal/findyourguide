package com.findyourguide.api.controller;

import com.findyourguide.api.dto.service.CreateServiceDTO;
import com.findyourguide.api.dto.service.ServiceDTO;
import com.findyourguide.api.dto.service.UpdateServiceDTO;
import com.findyourguide.api.dto.user.ResponseDTO;
import com.findyourguide.api.entity.Role;
import com.findyourguide.api.service.interfaces.IServiceService;
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
public class BuyServiceController {
    private final IServiceService serviceService;
    private final UserValidations userValidations;

    @GetMapping("/buys")
    public ResponseEntity<ResponseDTO<List<ServiceDTO>>> findAll() {
        return ResponseEntity.ok().body(new ResponseDTO<>(HttpStatus.OK, "All Services", serviceService.findAll()));
    }

    @GetMapping("/buys/tourist/{id}")
    public ResponseEntity<ResponseDTO<List<ServiceDTO>>> findAllByGuide(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(new ResponseDTO<>(HttpStatus.OK, "All Services", serviceService.findAllByGuide(id)));
    }

    @GetMapping("/buys/{id}")
    public ResponseEntity<ResponseDTO<ServiceDTO>> findById(@PathVariable Long id) {
        ServiceDTO serviceDTO = serviceService.findById(id);
        return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK, "Service Found", serviceDTO));
    }

    @PostMapping("/buys")
    public ResponseEntity<ResponseDTO<ServiceDTO>> create(@Valid @RequestBody CreateServiceDTO serviceDTO) {
        userValidations.validateRole(Role.GUIDE);
        ServiceDTO createdService = serviceService.create(serviceDTO);
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
        userValidations.validateRole(Role.GUIDE);
        serviceService.deleteById(id);
        return ResponseEntity.ok().body(new ResponseDTO<>(HttpStatus.OK, "Successfully Deleted", null));
    }

    @PutMapping("buys/{id}")
    public ResponseEntity<ResponseDTO<ServiceDTO>> update(@PathVariable Long id,
            @Valid @RequestBody UpdateServiceDTO updateServiceDTO) {
        userValidations.validateRole(Role.GUIDE);
        return ResponseEntity.ok().body(
                new ResponseDTO<>(HttpStatus.OK, "Successfully Updated",
                        serviceService.update(id, updateServiceDTO)));
    }

}

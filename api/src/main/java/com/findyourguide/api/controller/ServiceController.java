package com.findyourguide.api.controller;

import com.findyourguide.api.dto.service.CreateServiceDTO;
import com.findyourguide.api.dto.service.ServiceDTO;
import com.findyourguide.api.dto.service.UpdateServiceDTO;
import com.findyourguide.api.dto.user.ResponseDTO;
import com.findyourguide.api.entity.Service;
import com.findyourguide.api.service.IServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@Validated
@RequiredArgsConstructor
public class ServiceController {
    private final IServiceService serviceService;

    @PostMapping("/service")
    public ResponseEntity<ResponseDTO<ServiceDTO>> create(@Valid @RequestBody CreateServiceDTO serviceDTO) {
        ServiceDTO createdService = serviceService.create(serviceDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdService.getId())
                .toUri();
        return ResponseEntity.created(location).body(new ResponseDTO<>(HttpStatus.CREATED, "Created", createdService));
    }

    @GetMapping("/service")
    public ResponseEntity<ResponseDTO<List<ServiceDTO>>> findAll() {
        return ResponseEntity.ok().body(new ResponseDTO<>(HttpStatus.OK, "All Services", serviceService.findAll()));
    }

    @GetMapping("/service/{id}")
    public ResponseEntity<ResponseDTO<Optional<Service>>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK, "Service Found", serviceService.findById(id)));
    }

    @DeleteMapping("/service/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteById(@PathVariable Long id) {
        serviceService.deleteById(id);
        return ResponseEntity.ok().body(new ResponseDTO<>(HttpStatus.OK, "Successfully Deleted", null));
    }

    @PutMapping("service/{id}")
    public ResponseEntity<ResponseDTO<ServiceDTO>> update(@PathVariable Long id, @Valid @RequestBody UpdateServiceDTO updateServiceDTO) {
        return ResponseEntity.ok().body(new ResponseDTO<>(HttpStatus.OK, "Successfully Updated", serviceService.update(id, updateServiceDTO)));
    }

}

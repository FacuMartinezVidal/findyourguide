package com.findyourguide.api.controller;

import com.findyourguide.api.dto.service.CreateServiceDTO;
import com.findyourguide.api.dto.service.UpdateServiceDTO;
import com.findyourguide.api.entity.Service;
import com.findyourguide.api.service.IServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@Validated
@RequiredArgsConstructor
public class ServiceController {
    private final IServiceService serviceService;

    @PostMapping("/service")
    public ResponseEntity<String> create(@Valid @RequestBody CreateServiceDTO serviceDTO) {
        serviceService.create(serviceDTO);
        return ResponseEntity.ok("Tour Successfully Created!");
    }

    @GetMapping("/service")
    public ResponseEntity<List<Service>> findAll() {
        return ResponseEntity.ok(serviceService.findAll());
    }

    @GetMapping("/service/{id}")
    public ResponseEntity<Optional<Service>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceService.findById(id));
    }

    @DeleteMapping("/service/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        serviceService.deleteById(id);
        return ResponseEntity.ok("Delete service with id:" + id);
    }

    @PutMapping("service/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, UpdateServiceDTO updateServiceDTO) {
        serviceService.update(id, updateServiceDTO);
        return ResponseEntity.ok("Updated Successfully!");
    }

}

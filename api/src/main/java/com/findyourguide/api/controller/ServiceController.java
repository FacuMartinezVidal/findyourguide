package com.findyourguide.api.controller;

import com.findyourguide.api.dto.service.CreateServiceDTO;
import com.findyourguide.api.service.IServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Validated
@RequiredArgsConstructor
public class ServiceController {
    private final IServiceService serviceService;
    @PostMapping("/service")
    public void create(@Valid @RequestBody CreateServiceDTO serviceDTO){
        serviceService.create(serviceDTO);
    }

}

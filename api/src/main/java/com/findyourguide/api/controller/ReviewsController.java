package com.findyourguide.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.findyourguide.api.dto.Review.InputReview;
import com.findyourguide.api.dto.Review.ReviewDTO;
import com.findyourguide.api.dto.user.ResponseDTO;
import com.findyourguide.api.entity.Role;
import com.findyourguide.api.service.interfaces.IReviewService;
import com.findyourguide.api.util.UserValidations;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@Validated
@RequiredArgsConstructor
public class ReviewsController {

    private final IReviewService reviewService;
    private final UserValidations userValidations;

    @GetMapping("/reviews/user/{id}")
    public ResponseEntity<ResponseDTO<List<ReviewDTO>>> findAllByUser(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(new ResponseDTO<>(HttpStatus.OK, "All Services", reviewService.findAllByUser(id)));
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<ResponseDTO<ReviewDTO>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK, "Service Found", reviewService.findById(id)));
    }

    @PostMapping("/reviews/{id}")
    public ResponseEntity<ResponseDTO<ReviewDTO>> create(@PathVariable Long id,
            @Valid @RequestBody InputReview inputReview) {
        userValidations.validateRole(Role.TOURIST);
        ReviewDTO createdService = reviewService.create(id, inputReview);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdService.getId())
                .toUri();
        return ResponseEntity.created(location)
                .body(new ResponseDTO<>(HttpStatus.CREATED, "Created", createdService));
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<ResponseDTO<Boolean>> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(new ResponseDTO<>(HttpStatus.OK, "Successfully Deleted", reviewService.deleteById(id)));
    }

}

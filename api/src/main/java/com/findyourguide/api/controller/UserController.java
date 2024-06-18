package com.findyourguide.api.controller;

import com.findyourguide.api.dto.user.UpdateUserDTO;
import com.findyourguide.api.dto.user.UserDTO;
import com.findyourguide.api.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
@Validated
public class UserController {

    private final IUserService userService;

    @GetMapping("/user/{type}")
    public ResponseEntity<List<UserDTO>> findAll(@PathVariable String type) {
        return ResponseEntity.ok().body(userService.findAll(type));
    }

    @GetMapping("/user/{type}/{id}")
    public ResponseEntity<Optional<UserDTO>> findById(@PathVariable String type, @PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(type, id));
    }

    @DeleteMapping("/user/{type}/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String type, @PathVariable Long id) {
        userService.deleteById(type, id);
        return ResponseEntity.ok("Deleted user with id " + id);
    }

    @PutMapping("/user/{type}")
    public ResponseEntity<String> update(@PathVariable String type, @Valid @RequestBody UpdateUserDTO userDTO) {
        userService.update(type, userDTO);
        return ResponseEntity.ok("Updated Successfully");
    }


}

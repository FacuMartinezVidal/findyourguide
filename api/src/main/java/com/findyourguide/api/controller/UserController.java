package com.findyourguide.api.controller;

import com.findyourguide.api.dto.user.ResponseDTO;
import com.findyourguide.api.dto.user.UpdateUserDTO;
import com.findyourguide.api.dto.user.UserDTO;
import com.findyourguide.api.service.interfaces.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    // TODO use ResponseDTO
    // TODO improve body dto

    @GetMapping("/user")
    public ResponseEntity<ResponseDTO<List<UserDTO>>> findAll() {
        return ResponseEntity.ok().body(new ResponseDTO<>(HttpStatus.OK, "All Users", userService.findAll()));
    }

    @GetMapping("/user/{type}")
    public ResponseEntity<ResponseDTO<List<UserDTO>>> findAllByType(@PathVariable String type) {
        return ResponseEntity.ok().body(new ResponseDTO<>(HttpStatus.OK, "All Users", userService.findAllByType(type)));
    }

    @GetMapping("/user/{type}/{id}")
    public ResponseEntity<Optional<UserDTO>> findById(@PathVariable String type, @PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(type, id));
    }

    @GetMapping("/user/getBy/parameter/email")
    public ResponseEntity<UserDTO> findByEmail(@Valid @RequestBody String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @GetMapping("/user/getBy/parameter/jwt")
    public ResponseEntity<UserDTO> findByJwt() {
        return ResponseEntity.ok(userService.findByJwt());
    }

    @DeleteMapping("/user/{type}/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String type, @PathVariable Long id) {
        userService.deleteById(type, id);
        return ResponseEntity.ok("Deleted user with id " + id);
    }

    @PatchMapping("/user/{type}")
    public ResponseEntity<String> update(@PathVariable String type, @Valid @RequestBody UpdateUserDTO userDTO) {
        userService.update(type, userDTO);
        return ResponseEntity.ok("Updated Successfully!");
    }

}

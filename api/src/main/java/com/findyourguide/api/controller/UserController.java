package com.findyourguide.api.controller;

import com.findyourguide.api.dto.user.GuideDTO;
import com.findyourguide.api.dto.user.ResponseDTO;
import com.findyourguide.api.dto.user.UpdateUserDTO;
import com.findyourguide.api.dto.user.UserDTO;
import com.findyourguide.api.entity.Language;
import com.findyourguide.api.entity.Service.ServiceType;
import com.findyourguide.api.repository.SearchRequest;
import com.findyourguide.api.service.interfaces.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ResponseDTO<UserDTO>> findById(@PathVariable String type, @PathVariable Long id) {
        return ResponseEntity.ok().body(new ResponseDTO<>(HttpStatus.OK, "User with id " + id, userService.findById(type, id)));
    }

    @GetMapping("/user/getBy/parameter/email")
    public ResponseEntity<ResponseDTO<UserDTO>> findByEmail(@Valid @RequestBody String email) {
        return ResponseEntity.ok().body(new ResponseDTO<>(HttpStatus.OK, "User with email " + email, userService.findByEmail(email)));
    }

    @GetMapping("/user/getBy/parameter/jwt")
    public ResponseEntity<ResponseDTO<UserDTO>> findByJwt() {
        return ResponseEntity.ok().body(new ResponseDTO<>(HttpStatus.OK, "User with jwt", userService.findByJwt()));
    }

    @DeleteMapping("/user/{type}/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteById(@PathVariable String type, @PathVariable Long id) {
        userService.deleteById(type, id);
        return ResponseEntity.ok().body(new ResponseDTO<>(HttpStatus.OK, "Deleted Successfully!", null));
    }

    @PatchMapping("/user/{type}")
    public ResponseEntity<ResponseDTO<UserDTO>> update(@PathVariable String type, @Valid @RequestBody UpdateUserDTO userDTO) {
        return ResponseEntity.ok().body(new ResponseDTO<>(HttpStatus.OK, "Updated Successfully!", userService.update(type, userDTO)));
    }

    @GetMapping("/user/")
    public ResponseEntity<ResponseDTO<List<GuideDTO>>> findByCriteria(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) Language language,
            @RequestParam(required = false) ServiceType serviceType,
            @RequestParam(required = false) Double score,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country
    ) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setFirstName(firstName);
        searchRequest.setLastName(lastName);
        searchRequest.setLanguage(language);
        searchRequest.setServiceType(serviceType);
        searchRequest.setScore(score);
        searchRequest.setCity(city);
        searchRequest.setCountry(country);
        return ResponseEntity.ok().body(new ResponseDTO<>(HttpStatus.OK, "Guides found", userService.findByCriteria(searchRequest)));
    }

}

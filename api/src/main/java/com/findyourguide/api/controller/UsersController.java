package com.findyourguide.api.controller;

import com.findyourguide.api.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UsersController {

    @GetMapping("/user")
    public ResponseEntity<String> findAll(){
        return ResponseEntity.ok().body("all users");
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<String> findById(@PathVariable Long id){
        return ResponseEntity.ok().body("user with id " + id);
    }

    @PutMapping("user/{id}")
    public ResponseEntity<Map<String, Map<String, String>>> update(@PathVariable String id, @RequestBody Map<String,String> user){
        Map<String,Map<String,String>> response = new HashMap<>();
        response.put("user",user);
        Map<String,String> idMap = new HashMap<>();
        idMap.put("id",id);
        response.put("id", idMap);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable Long id){
        String message = "user with id " + id + " has been deleted";
        return ResponseEntity.ok().body(new ResponseDTO(message,HttpStatus.OK));
    }

}

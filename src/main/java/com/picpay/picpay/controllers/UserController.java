package com.picpay.picpay.controllers;

import com.picpay.picpay.domain.User;
import com.picpay.picpay.dtos.UserDTO;
import com.picpay.picpay.repository.UserRepository;
import com.picpay.picpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO data){
        User newUser = new User(data);
        this.userRepository.save(newUser);
        return ResponseEntity.ok().body(newUser);
    }
}

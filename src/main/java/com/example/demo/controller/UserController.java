package com.example.demo.controller;


import com.example.demo.beans.User;
import com.example.demo.beans.UserRepository;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Hidden
@RestController
@RequestMapping("/admin/diskSharing/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    public @RequestMapping(method = RequestMethod.OPTIONS)
    ResponseEntity<?> options() {

        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE).build();

    }

    @GetMapping
    public ResponseEntity<List<User>> getCollection() {
        return ResponseEntity.ok(this.repository.findAll());
    }




}
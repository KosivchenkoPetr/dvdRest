package com.example.demo.controller;


import com.example.demo.beans.Credential;
import com.example.demo.beans.Disk;
import com.example.demo.beans.TakenItem;
import com.example.demo.beans.User;
import com.example.demo.dao.Dao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

@Tag(name = "1. Start", description = "Fill with initial data before use API")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RestController
@Slf4j
@RequestMapping("/start")
public class StartDataController {

    @Autowired
    private Dao dao;

    // <1>
    @RequestMapping(method = RequestMethod.OPTIONS)
    ResponseEntity<?> options() {

        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.HEAD, HttpMethod.OPTIONS,
                HttpMethod.PUT, HttpMethod.DELETE).build();
    }

    @Operation(
            summary = "Fill with initial data before use API",
            description = "Fill database with new users, new DVD, set DVD to master users. Login-password: user-user and admin-admin"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
    })
    @GetMapping
    public ResponseEntity<?> start() {

        Disk disk1 = new Disk("Disk1");
        Disk disk2 = new Disk("Disk2");
        Disk disk3 = new Disk("Disk3");


        User admin = new User("Admin");
        User user = new User("User");

        Credential crAdmin = new Credential("admin", "admin", "ADMIN");
        Credential crUser = new Credential("user", "user", "USER");

        admin.setDisk(disk1);
        admin.setCredential(crAdmin);
        user.setDisk(disk2);
        user.setCredential(crUser);
        disk1.setCurrentOwner(user);
        TakenItem takenItem = new TakenItem();
        TakenItem takenItem2 = new TakenItem();

        takenItem.setDisk(disk1);
        takenItem2.setDisk(disk3);
        takenItem.setCurrentOwner(user);
        dao.add(user);

        dao.add(admin);
        dao.add(disk3);
        dao.add(takenItem);
        dao.add(takenItem2);

        log.info("Star data loaded");

        return ResponseEntity.ok(Collections.EMPTY_LIST);
    }


}
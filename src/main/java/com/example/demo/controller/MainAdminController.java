package com.example.demo.controller;

import com.example.demo.beans.TakenItem;
import com.example.demo.service.impl.DiskServiceImpl;
import com.example.demo.service.impl.TakenItemServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "3. Admin controller", description = "DVD operations with ADMIN user")
@Scope
@RestController
@Slf4j
@RequestMapping("/admin/diskSharing")
public class MainAdminController {
    //name = "admin", password = "admin"
    @Autowired
    private TakenItemServiceImpl takenItemService;
    private UserServiceImpl userService;
    private DiskServiceImpl diskService;


    @RequestMapping(method = RequestMethod.OPTIONS)
    ResponseEntity<?> options() {
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE).build();
    }

    @Operation(
            summary = "Get all taken items",
            description = "Get all taken items"
    )
    @GetMapping(value = "/all/takenItem")
    public ResponseEntity<List<TakenItem>> getAllCollectionTakenItems() {
        log.info("Admin get all taken items");
        return ResponseEntity.ok(takenItemService.getAllTakenItems());
    }

    @Operation(
            summary = "Add disk to user",
            description = "Add disk {idDisk} to user {id}"
    )
    @GetMapping(value = "/user/{id}/disk/{idDisk}")
    public ResponseEntity<?> addDiskToUser(@PathVariable Long id, @PathVariable Long idDisk) {
        log.info("Admin add disk with id " + idDisk + " to user with id " + id);
        return diskService.addDiskToUser(id, idDisk);
    }

    @Operation(
            summary = "Get all taken items of owner",
            description = "Get all taken items of owner {id}"
    )
    @GetMapping(value = "/currentOwner/{id}")
    public List<?> getAllTakenItemsOfCurrentOwner(@PathVariable Long id) {
        log.info("Admin get all taken items of owner with id " + id);
        return takenItemService.getAllTakenItemsOfCurrentOwner(id);
    }

    @Operation(
            summary = "Get all free items",
            description = "Get all free items"
    )
    @GetMapping(value = "/currentOwner/free")
    public List<?> getAllTakenItemsFree() {
        log.info("Admin get all taken free items");
        return takenItemService.getAllTakenItemsFree();

    }

    @Operation(
            summary = "Get all taken items of master",
            description = "Get all taken items of master {id}"
    )
    @GetMapping(value = "/master/{id}")
    public List<?> getAllTakenItemsOfMaster(@PathVariable Long id) {
        log.info("Admin get all taken items of master with id " + id);
        return takenItemService.getAllTakenItemsOfMaster(id);

    }

    @Operation(
            summary = "Get all disks of user",
            description = "Get all disks of user {id}"
    )
    @GetMapping(value = "/user/{id}/disks")
    public ResponseEntity<?> getListDisks(@PathVariable Long id) {
        log.info("Admin get all disks of user with id " + id);
        return new ResponseEntity<>(diskService.getListDisksForUser(id), HttpStatus.OK);
    }
}
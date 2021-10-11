package com.example.demo.controller;

import com.example.demo.beans.Credential;
import com.example.demo.beans.Disk;
import com.example.demo.beans.TakenItem;
import com.example.demo.beans.User;
import com.example.demo.dao.DaoDisk;
import com.example.demo.dao.DaoTakenItem;
import com.example.demo.dao.DaoUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import java.util.List;

@Tag(name = "2. User controller", description = "DVD operations with user")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RestController
@Slf4j
@RequestMapping("/user/diskSharing")
public class MainUserController {

    private Long idPrincipal;
    @Autowired

    private DaoTakenItem daoTakenItem;
    private DaoUser daoUser;
    private DaoDisk daoDisk;

    private Credential credential;

    @RequestMapping(method = RequestMethod.OPTIONS)
    ResponseEntity<?> options() {

        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE).build();

    }

    @Operation(
            summary = "Welcome screen",
            description = "Welcome screen"
    )
    @GetMapping(value = "/welcome")
    public ResponseEntity<?> welcome() {
        updateDatePrincipal();
        User user = daoUser.getUser(idPrincipal);
        log.info(user.getName()+" welcome screen");

        return ResponseEntity.ok("Welcome " + user.getName());

    }

    @Operation(
            summary = "Get all taken items",
            description = "Get all taken items"
    )
    @GetMapping(value = "/all/takenItem")
    public ResponseEntity<List<TakenItem>> getAllCollectionTakenItems() {
        log.info(daoUser.getUser(idPrincipal).getName()+" get all taken items");
        return ResponseEntity.ok(daoTakenItem.getAllTakenItems());
    }

    @Operation(
            summary = "Get all taken items of current owner",
            description = "Get all taken items of current owner"
    )
    @GetMapping(value = "/currentOwner")
    public List<?> getAllTakenItemsOfCurrentOwner() {
        log.info(daoUser.getUser(idPrincipal).getName()+" get all taken items of current owner");
        return daoTakenItem.getAllTakenItemsOfCurrentOwner(idPrincipal);

    }

    @Operation(
            summary = "Get list of all free taken items",
            description = "Get all free taken items"
    )
    @GetMapping(value = "/currentOwner/free")
    public List<?> getAllTakenItemsFree() {
        return daoTakenItem.getAllTakenItemsFree();

    }

    @Operation(
            summary = "Get list of all taken items of master",
            description = "Get list of all taken items of master"
    )
    @GetMapping(value = "/master")
    public List<?> getAllTakenItemsOfMaster() {
        log.info(daoUser.getUser(idPrincipal).getName()+" get all master items");
        return daoTakenItem.getAllTakenItemsOfMaster(idPrincipal);

    }

    @Operation(
            summary = "Get list disks for user",
            description = "Get list disks for user"
    )
    @GetMapping(value = "/user/disks")
    public ResponseEntity<?> getListDisksForUser() {
        List<Disk> list = daoDisk.getListDisksForUser(idPrincipal);
        log.info(daoUser.getUser(idPrincipal).getName()+" get list disks for user");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    private void updateDatePrincipal() {
        String userName;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Authentication principal = SecurityContextHolder.getContext().getAuthentication();
            userName = principal.getName();
            try {
                this.idPrincipal = daoUser.findCredentialByName(userName).getIdClient();
            } catch (CannotCreateTransactionException e) {
                log.error("Error: CannotCreateTransaction. Username: "+userName);
            }
        }
        // place for log
    }
}
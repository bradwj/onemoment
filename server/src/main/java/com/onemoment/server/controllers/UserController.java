package com.onemoment.server.controllers;

import com.onemoment.server.models.User;
import com.onemoment.server.models.Users;
import com.onemoment.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(
            path="/",
            produces="application/json"
    )
    public Users getUsers() {
        return userRepository.getAllUsers();
    }

    @PostMapping(
            path="/create",
            consumes="application/json",
            produces="application/json"
    )
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setUid(UUID.randomUUID());
        userRepository.addUser(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);

        /* Keeping incase want to use in future
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(
                        user.getUid())
                .toUri();

        return ResponseEntity.created(location).build();
         */
    }

}

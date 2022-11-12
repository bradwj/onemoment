package com.onemoment.server.controllers;

import com.onemoment.server.models.User;
import com.onemoment.server.models.Users;
import com.onemoment.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private static final int MIN_USERNAME_LENGTH = 6;
    private static final int MAX_USERNAME_LENGTH = 14;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_BIOGRAPHY_LENGTH = 200;

    private static final String INVALID_USERNAME_LENGTH_MSG = "Username length must be at least " + MIN_USERNAME_LENGTH + " characters and not greater than " + MAX_USERNAME_LENGTH + " characters.";
    private static final String USERNAME_EXISTS_MSG = "Username is already taken.";
    private static final String INVALID_PASSWORD_MSG = "Password must be least 8 characters in length and must contain 1 uppercase and 1 lowercase character, and 1 digit.";
    private static final String INVALID_PHONE_NUM_MSG = "Phone number is invalid.";
    private static final String INVALID_BIRTHDAY_MSG = "Birthday must be a valid date.";
    private static final String INVALID_BIOGRAPHY_MSG = "Biography must be less than " + MAX_BIOGRAPHY_LENGTH + " characters long.";
    private static final String USER_CREATE_SUCCESS_MSG = "User created successfully.";
    @Autowired
    private UserRepository userRepository;

    private class UserControllerResponse {
        boolean success;
        UUID uid;
        String message;

        public UserControllerResponse(boolean success, UUID uid, String message) {
            this.success = success;
            this.uid = uid;
            this.message = message;
        }
        public void setSuccess(boolean success) {
            this.success = success;
        }
        public void setUid(UUID uid) {
            this.uid = uid;
        }
        public void setMessage(String message) {
            this.message = message;
        }
        public boolean isSuccess() {
            return success;
        }
        public UUID getUid() {
            return uid;
        }
        public String getMessage() {
            return message;
        }
    }

    @PostMapping(
            path="/create",
            consumes="application/json",
            produces="application/json"
    )
    public ResponseEntity<UserControllerResponse> createUser(@RequestBody User user) {
        UserControllerResponse resp = new UserControllerResponse(false, null, null);
        if (!isValidUsernameLength(user.getUsername())) {
            resp.setMessage(INVALID_USERNAME_LENGTH_MSG);
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }
        if (userRepository.userExistsWithUsername(user.getUsername())) {
            resp.setMessage(USERNAME_EXISTS_MSG);
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }
        if (!isValidPassword(user.getPassword())) {
            resp.setMessage(INVALID_PASSWORD_MSG);
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }
        if (!isValidPhoneNumber(user.getPhoneNumber())) {
            resp.setMessage(INVALID_PHONE_NUM_MSG);
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }
        if (user.getBirthday() == null) {
            resp.setMessage(INVALID_BIRTHDAY_MSG);
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }
        if (!isValidBiography(user.getBiography())) {
            resp.setMessage(INVALID_BIOGRAPHY_MSG);
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }

        user.setUid(UUID.randomUUID());
        userRepository.addUser(user);
        resp.setSuccess(true);
        resp.setUid(user.getUid());
        resp.setMessage(USER_CREATE_SUCCESS_MSG);
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    private boolean isValidUsernameLength(String username) {
        return (username.length() >= MIN_USERNAME_LENGTH
                && username.length() <= MAX_USERNAME_LENGTH);
    }

    private boolean isValidPassword(String password) {
        // Making sure password is at least 8 characters in length
        // and meets specifications of 1 uppercase, 1 lowercase, and 1 digit
        return (password.length() >= MIN_PASSWORD_LENGTH
                && password.matches(".*[a-z].*")
                && password.matches(".*[A-Z].*")
                && password.matches(".*\\d.*"));
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Ensuring phone number is correct length and only contains digits
        return phoneNumber.matches("([0-9]{10})|([0-9]{3}-[0-9]{3}-[0-9]{4})");
    }

    private boolean isValidBiography(String biography) {
        return biography.length() < MAX_BIOGRAPHY_LENGTH;
    }
}

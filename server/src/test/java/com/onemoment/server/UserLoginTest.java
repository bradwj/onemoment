package com.onemoment.server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserLoginTest {
    userLogin login;
    ArrayList<User> userDB = new ArrayList<>();
    User user = new User("existingUserName", "Password123", "2144442005", "09122001", "Here is a biography.");

    @BeforeEach
    void setUp() {
        userDB.add(user);
        login = new userLogin("existingUserName", "Password123");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void userIsValid() {
        assertTrue(login.userIsValid(userDB));
    }

    @Test
    void correctPassWord() {
        assertTrue(login.correctPassWord(userDB));
    }

    @Test
    void print() {
        assertEquals("Login Successful", login.print(userDB));
    }
}
package com.onemoment.server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AccountCreationTest {
    accountCreation account;
    ArrayList<User> userDB =  new ArrayList<>();
    User existingUser = new User("oldUserName", "Password123", "2144442005", "09122001", "Here is a biography.");

    @BeforeEach
    void setUp() {
        account = new accountCreation();
        userDB.add(existingUser);
    }

    @AfterEach
    void tearDown() {
        userDB.add(account.finishCreation(userDB));
    }

    @Test
    void setUsername() {
        account.setUsername("newUserName");
        assertEquals("newUserName", account.getUsername());
    }

    @Test
    void setPassword() {
        account.setPassword("Password123");
        assertEquals("Password123", account.getPassword());
    }

    @Test
    void setPhoneNum() {
        account.setPhoneNum("2144172005");
        assertEquals("2144172005", account.getPhoneNum());
    }

    @Test
    void setBirthday() {
        account.setBirthday("09102001");
        assertEquals("09102001", account.getBirthday());
    }

    @Test
    void setBiography() {
        account.setBiography("Here is a biography.");
        assertEquals("Here is a biography.", account.getBiography());
    }

    @Test
    void print() {
        account = new accountCreation();
        account.setUsername("newUserName");
        account.setPassword("Password123");
        account.setPhoneNum("2144172005");
        account.setBirthday("09102001");
        account.setBiography("Here is a biography");
        assertEquals("Account Creation Successful!", account.print());
    }
}
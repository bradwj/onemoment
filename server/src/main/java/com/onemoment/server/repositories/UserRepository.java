package com.onemoment.server.repositories;

import com.onemoment.server.models.User;
import com.onemoment.server.models.Users;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public class UserRepository {

    private static Users list = new Users();

    static {
        list.getUserList().add(
                new User(
                        UUID.randomUUID(),
                        "user_name",
                        "password123",
                        "123-456-7890",
                        new Date(),
                        "this is my bio"
                )
        );
    }

    public Users getAllUsers() {
        return list;
    }

    public void addUser(User user) {
        list.getUserList()
                .add(user);
    }

    public boolean userExistsWithUsername(String username) {
        return list.userExistsWithUsername(username);
    }

    public User getUserWithUsername(String username) {
        return list.getUserWithUsername(username);
    }

}

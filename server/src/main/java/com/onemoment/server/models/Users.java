package com.onemoment.server.models;

import java.util.ArrayList;
import java.util.List;

public class Users {

    private List<User> userList;

    public List<User> getUserList() {
        if (userList == null) {
            userList = new ArrayList<>();
        }
        return userList;
    }

    public boolean userExistsWithUsername(String username) {
        for (User user : userList) {
            if (username.equals(user.getUsername()))
                return true;
        }
        return false;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

}

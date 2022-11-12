package com.onemoment.server.models;

import com.onemoment.server.repositories.UserRepository;

import java.util.List;

public class SignIn {
    private String usernameAttempt;
    private String passwordAttempt;
    private UserRepository uR;
    private User user;
    private List<User> userList = uR.getAllUsers().getUserList();

    
    public SignIn(String uAttempt, String pAttempt)
    {
        user = null;
        usernameAttempt = uAttempt;
        passwordAttempt = pAttempt;
    }

    public void setUsername(String uAttempt)
    {
        usernameAttempt = uAttempt;
    }

    public void setPassword(String pAttempt)
    {
        usernameAttempt = pAttempt;
    }

    private boolean validUsername()
    {
        boolean vUsername = false;
        for(User u : userList)
        {
            if(u.getUsername().equals(usernameAttempt))
            {
                vUsername = true;
                user = u;
            }
        }
        return vUsername;
    }

    private boolean correctPassword() 
    {
        boolean cPassword = false;
        if(user != null)
        {
            if(user.getPassword().equals(passwordAttempt))
            {
                cPassword = true;
            }
        }
        return cPassword;
    }

    public boolean isSuccessful()
    {
        return (validUsername() && correctPassword());
    }
}


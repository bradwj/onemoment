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
        if(usernameAttempt.length() >= 6 && usernameAttempt.length() <= 14 && passwordAttempt.length() >= 8)
            return (validUsername() && correctPassword());
        return false;
    }

    @Before
    public void setUp() throws Exception{
        
        SignIn s = new SignIn("ebrown18","password123");
    }

    @After
    public void tearDown() throws Exception{
        
    }
    //change assertFalse to assertTrue if add user ebrown18 to UserList
    @Test
    public void testValidUsername(){
        //Should return false since ebrown18 is not in UsersList
        assertFalse(s.validUsername);
    }

    @Test
    public void testCorrectPassword() {
        //Should return false since user ebrown18 doesn't exist
        assertFalse(s.correctPassword());
    }

    @Test
    public void testIsSuccessful() {
        //Should return false since other 2 are false
        assertFalse(s.isSuccessful());
    }
}


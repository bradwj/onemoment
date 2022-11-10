package com.onemoment.server.models;

import com.onemoment.server.controllers.UserController;
import com.onemoment.server.models.*;
import java.util.Date;
import java.util.List;


public class AccountCreation {
    private String username;
    private String password;
    private String phoneNum;
    private Date birthday;
    private String biography;
    public boolean contains;

    public AccountCreation() {
        username = "";
        password = "";
        phoneNum = "";
        birthday = null;
        biography = "";
    }


    public String createAccount(String uName, String pWord, String pNum, String bday, String bio)
    {
        UserController userController = new UserController();
        User userTBA;

        // Setting chacteristics of user
        if(setUsername(uName) && setPassword(pWord) && setPhoneNum(pNum) && setBirthday(bday) && setBiography(bio))
        {
            userTBA = finishCreation(userController.getUsers().getUserList());
            if (userTBA != null){
            // Adding user to database and assigning uid
            userController.createUser(userTBA);
            return "Account creation successful";
            }
        }
        return "Account creation unsuccessful";
    }

    public boolean setUsername(String uName) {
        // Ensuring username is between 6 and 14 character in length, inclusive
        int length = uName.length();
        if(length>=6 && length<=14)
        {
            username = uName;
            return true;
        }
        return false;
    }


    public boolean setPassword(String pWord) {
        // Making sure password is at least 8 characters in length and meets specifications of 1 uppercase, 1 lowercase, and 1 digit
        int length = pWord.length();
        if(length>=8) {
            if(pWord.matches(".*[a-z].*")) {
                if(pWord.matches(".*[A-Z].*")) {
                    if(pWord.matches(".*\\d.*")) {
                        password = pWord;
                        return true;
                    }
                }
            }
        }

        return false;
    }


    public boolean setPhoneNum(String pNum) {
        // Ensuring phone number is correct length and only contains digits
        int length = pNum.length();
        if(length==10) {
            if(pNum.matches("[0-9]{10}") ) {
                phoneNum = pNum;
                return true;
            }
        }
        return false;
    }

    public boolean setBirthday(String bDay) {
        // Error checking to ensure bday is an 8 character string in the following format MMDDYYYY
        int length = bDay.length();
        if(length==8) {
            // Ensuring each character in bday is a digit
            if(bDay.matches("[0-9]{8}") ) {
                // Obtaining date, year, and month from bday
                int year = Integer.parseInt(bDay.substring(length-4))-1900;
                int date = Integer.parseInt(bDay.substring(2,4));
                int month = Integer.parseInt(bDay.substring(0,2))-1;
                // If old enough sets birthday to the date recorded in bDay
                if(year < 2009)
                {
                    birthday = new Date(year, month, date);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean setBiography(String bio) {
        // Ensuring bio is <200 characters long
        int length = bio.length();
        if(length < 200) {
            biography = bio;
            return true;
        }

        return false;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getBiography() {
        return biography;
    }

    public User finishCreation(List<User> userDB) {
        contains = false;
        // Making sure no other users have the same username
        for(User u : userDB)
        {
            if(u.getUsername().equals(username))
                contains = true;
        }
        // Creating a user with no uid if username is unique, otherwise returns null
        if(!contains)
            return new User(null, username, password, phoneNum, birthday, biography);
        else
            return null;
    }

    public String print() {
        if(!contains)
            return "Account Creation Successful!";
        return "Account Creation Unsuccessful - please try again";
    }

}
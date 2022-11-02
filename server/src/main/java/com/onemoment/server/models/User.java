package com.onemoment.server.models;

import java.util.Date;
import java.util.UUID;

public class User {

    private UUID uid;
    private String username;
    private String password;
    private String phoneNumber;
    private Date birthday;
    private String biography;

    public User(UUID uid, String username, String password, String phoneNumber, Date birthday, String biography) {
        super();
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.biography = biography;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}

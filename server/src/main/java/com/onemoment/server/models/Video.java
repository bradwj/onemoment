package com.onemoment.server.models;


public class Video {
    private User u;
    private String name;
    private byte[] data;

    public Video(User u, String name, byte[] data) {
        this.u = u;
        this.name = name;
        this.data = data;
    }

    public User getUser() {
        return u;
    }

    public String getName() {
        return name;
    }

    public byte[] getData() {
        return data;
    }

    public int getSize() {
        return data.length;
    }
}

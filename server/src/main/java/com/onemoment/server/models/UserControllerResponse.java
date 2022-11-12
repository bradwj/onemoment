package com.onemoment.server.models;

import java.util.UUID;

public class UserControllerResponse {
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

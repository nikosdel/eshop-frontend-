package com.example.paraggelies.models;

public class customer {
    private String id,username,token,location;

    public customer(String id, String username, String token, String location) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public String getLocation() {
        return location;
    }
}

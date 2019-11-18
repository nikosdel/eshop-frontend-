package com.example.paraggelies.models;

public class loginRespone {
    private String message;
    private customer customer;

    public loginRespone(String message, customer customer) {
        this.message = message;
        this.customer = customer;
    }

    public String getMessage() {
        return message;
    }

    public customer getCustomer() {
        return customer;
    }
}



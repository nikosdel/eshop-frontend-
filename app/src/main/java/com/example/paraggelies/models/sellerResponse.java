package com.example.paraggelies.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class sellerResponse {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("customer")
    @Expose
    private seller seller;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public com.example.paraggelies.models.seller getSeller() {
        return seller;
    }

    public void setSeller(com.example.paraggelies.models.seller seller) {
        this.seller = seller;
    }
}

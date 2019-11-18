package com.example.paraggelies.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class order {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("customerId")
    @Expose
    private String customerId;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("product")
    @Expose
    private productModel product = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public productModel getProduct() {
        return product;
    }

    public void setProduct(productModel product) {
        this.product = product;
    }

}

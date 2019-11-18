package com.example.paraggelies.models;

public class makeOrderModel {
    String quantity;
    String productName;
    String productPrice;

    public makeOrderModel(String quantity, String productName, String productPrice) {
        this.quantity = quantity;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProductId() {
        return productName;
    }

    public void setProductId(String productId) {
        this.productName = productId;
    }

    public String getCustomerId() {
        return productPrice;
    }

    public void setCustomerId(String customerId) {
        this.productPrice = customerId;
    }
}

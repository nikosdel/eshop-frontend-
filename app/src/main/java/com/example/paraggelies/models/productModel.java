package com.example.paraggelies.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class productModel {
    @SerializedName("image")
    @Expose
    private String productImage;
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("price")
    private String price;
    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("__v")
    @Expose
    private Integer v;


    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getId() {
        return id;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setId(String id) {
        this.id = id;
    }
}

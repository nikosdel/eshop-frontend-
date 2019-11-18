package com.example.paraggelies.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class productsResponse {


    @SerializedName("productModel")
    @Expose
    private List<productModel> productModel = null;

    public List<productModel> getProductModel() {
        return productModel;
    }

    public void setProductModel(List<productModel> productModel) {
        this.productModel = productModel;
    }
}

package com.example.paraggelies.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class orderResponse {


    @SerializedName("order")
    @Expose
    private List<order> order = null;

    public List<order> getOrder() {
        return order;
    }

    public void setOrder(List<order> order) {
        this.order = order;
    }
}

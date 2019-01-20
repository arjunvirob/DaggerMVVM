package com.myapps.daggermvvm.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.myapps.daggermvvm.model.Product;

import java.util.List;

public class ProductListResponse {
    @SerializedName("products")
    @Expose
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}

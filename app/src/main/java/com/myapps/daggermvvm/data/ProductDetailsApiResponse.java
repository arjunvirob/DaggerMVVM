package com.myapps.daggermvvm.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.myapps.daggermvvm.model.Product;

public class ProductDetailsApiResponse {
    @SerializedName("product")
    @Expose
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

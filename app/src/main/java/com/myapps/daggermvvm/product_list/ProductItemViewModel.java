package com.myapps.daggermvvm.product_list;

import android.databinding.BaseObservable;

import com.myapps.daggermvvm.model.Product;

public class ProductItemViewModel extends BaseObservable {
    private Product product;

    public ProductItemViewModel() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}

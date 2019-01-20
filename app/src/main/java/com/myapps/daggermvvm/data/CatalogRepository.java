package com.myapps.daggermvvm.data;


import android.support.annotation.NonNull;

import com.myapps.daggermvvm.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class CatalogRepository {
    private ApiService apiService;

    public CatalogRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    List<Product> productsList = new ArrayList<>();

    private Product productViewing;

    public Observable<List<Product>> getProductsFromCategory(int page, String category) {
        return apiService.getProductList(page, category)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(ProductListResponse::getProducts)
                .doOnNext(products ->
                        productsList = products);
    }

    public Observable<Product> getProductInfo(final long prodId) {
        return apiService.getProductDetails(prodId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(ProductDetailsApiResponse::getProduct)
                .doOnNext(product -> productViewing = product);

    }
}

package com.myapps.daggermvvm.data;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/v1/products.json")
    Observable<ProductListResponse> getProductList(@Query("page") int page, @Query("category") String category);

    @GET("api/v1/products/{id}.json")
    Observable<ProductDetailsApiResponse> getProductDetails(@Path("id") long id);
}

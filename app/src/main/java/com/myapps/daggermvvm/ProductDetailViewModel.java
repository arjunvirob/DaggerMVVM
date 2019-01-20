package com.myapps.daggermvvm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;

import com.google.gson.Gson;
import com.myapps.daggermvvm.data.ApiService;
import com.myapps.daggermvvm.data.CatalogRepository;
import com.myapps.daggermvvm.model.Product;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ProductDetailViewModel extends ViewModel {
    private MutableLiveData<Product> product;

    private int prodID;

    public ProductDetailViewModel(int prodID) {
        this.prodID = prodID;
    }

    public void setProduct(MutableLiveData<Product> product) {
        this.product = product;
    }

    public MutableLiveData<Product> getProduct() {
        return product;
    }

    public MutableLiveData<Product> getProductDetails(Context c) {
        if (product == null) {
            product = new MutableLiveData<>();
            loadProduct(c);
        }

        return product;
    }

    public void loadProduct(Context c) {
        ApiService apiService = NetworkModule.getRetrofit(c).create(
                ApiService.class
        );

        CatalogRepository repository = new CatalogRepository(apiService);
        /*if (isLoading.get()) {
            return;
        }
        showError.set(false);
        isLoading.set(true);*/
        repository.getProductInfo(prodID).subscribe(new Observer<Product>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Product freshProduct) {
                product.setValue(freshProduct);
                Log.e("PRoduct", " " + new Gson().toJson(
                        freshProduct
                ));


            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
                Log.e("PRoduct", " Exc: " + e.toString());
 /*               isLoading.set(false);
                toastUtil.showLongMessage("Unable to sync with server!");
                showError.set(true);*/
            }

            @Override
            public void onComplete() {
                //             isLoading.set(false);
            }
        });
    }
}

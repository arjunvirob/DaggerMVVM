package com.myapps.daggermvvm.product_details;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.myapps.daggermvvm.BR;
import com.myapps.daggermvvm.data.ApiReqModule;
import com.myapps.daggermvvm.data.ApiService;
import com.myapps.daggermvvm.data.CatalogRepository;
import com.myapps.daggermvvm.model.Product;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ProductDetailViewModel extends BaseObservable {
    private MutableLiveData<Product> product;
    private boolean isLoaded;

    @Bindable
    public boolean isLoaded() {
        return isLoaded;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
        notifyPropertyChanged(BR._all);
    }

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
        isLoaded = false;
        ApiService apiService = ApiReqModule.getRetrofit().create(
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
            }

            @Override
            public void onComplete() {
                //             isLoading.set(false);
                setLoaded(true);
            }
        });
    }
}

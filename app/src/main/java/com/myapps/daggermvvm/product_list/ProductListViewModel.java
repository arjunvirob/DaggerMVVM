package com.myapps.daggermvvm.product_list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.myapps.daggermvvm.BR;
import com.myapps.daggermvvm.data.ApiReqModule;
import com.myapps.daggermvvm.data.ApiService;
import com.myapps.daggermvvm.data.CatalogRepository;
import com.myapps.daggermvvm.model.Product;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ProductListViewModel extends BaseObservable {
    private String category;
    private boolean isLoading = true;

    @Bindable
    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        notifyPropertyChanged(BR._all);
    }

    private MutableLiveData<List<Product>> productAre;

    public ProductListViewModel(String category) {
        this.category = category;
    }

    public void loadProducts(Context context) {
        ApiService apiService = ApiReqModule.getRetrofit(context).create(ApiService.class);
        CatalogRepository catalogRepository = new CatalogRepository(apiService);

        catalogRepository.getProductsFromCategory(1, category).subscribe(new Observer<List<Product>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                setLoading(true);
            }

            @Override
            public void onNext(@NonNull List<Product> products) {
                setLoading(false);
                productAre.setValue(products);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                setLoading(false);
            }
        });
    }

    public LiveData<List<Product>> getProducts(Context context) {
        //if the list is null
        if (productAre == null) {
            productAre = new MutableLiveData<>();
            //we will load it asynchronously from server in this method
            loadProducts(context);
        }

        //finally we will return the list
        return productAre;
    }
}

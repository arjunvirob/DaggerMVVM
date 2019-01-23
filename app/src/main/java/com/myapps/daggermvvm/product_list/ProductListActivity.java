package com.myapps.daggermvvm.product_list;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import com.myapps.daggermvvm.MyApp;
import com.myapps.daggermvvm.R;
import com.myapps.daggermvvm.data.CatalogRepository;
import com.myapps.daggermvvm.databinding.ActivityProductListBinding;

import javax.inject.Inject;

public class ProductListActivity extends AppCompatActivity {
    ActivityProductListBinding activityProductListBinding;

    @Inject
    CatalogRepository catalogRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityProductListBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_product_list);

        activityProductListBinding.productRv.setLayoutManager(new GridLayoutManager(this, 2));
        activityProductListBinding.productRv.setHasFixedSize(true);

        String category = getIntent().getStringExtra("Category");
        getSupportActionBar().setTitle(category);

        MyApp.getAppComponent().inject(this);

        ProductListViewModel productListViewModel = new ProductListViewModel(category);

        activityProductListBinding.setProductListViewModel(productListViewModel);

        productListViewModel.getProducts(this, catalogRepository).
                observe(this, products ->
                        activityProductListBinding.productRv.setAdapter(new ProductsAdapter(products
                                , this)));
    }

}

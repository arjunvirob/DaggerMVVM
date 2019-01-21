package com.myapps.daggermvvm.product_list;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import com.myapps.daggermvvm.R;
import com.myapps.daggermvvm.databinding.ActivityProductListBinding;

public class ProductListActivity extends AppCompatActivity {
    ActivityProductListBinding activityProductListBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityProductListBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_product_list);

        activityProductListBinding.productRv.setLayoutManager(new GridLayoutManager(this, 2));
        activityProductListBinding.productRv.setHasFixedSize(true);

        String category = getIntent().getStringExtra("Category");
        getSupportActionBar().setTitle(category);

        ProductListViewModel productListViewModel = new ProductListViewModel(category);

        activityProductListBinding.setProductListViewModel(productListViewModel);

        productListViewModel.loadProducts(this);

        productListViewModel.getProducts(this).
                observe(this, products ->
                        activityProductListBinding.productRv.setAdapter(new ProductsAdapter(products
                                , this)));
    }

}

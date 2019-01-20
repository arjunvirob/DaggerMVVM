package com.myapps.daggermvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.myapps.daggermvvm.databinding.ActivityProductDetailBinding;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProductDetailBinding activityProductDetailBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_product_detail);

        int prodId = getIntent().getIntExtra("PRODUCT_ID", 0);

        ProductDetailViewModel productDetailViewModel = new ProductDetailViewModel(
                prodId
        );

        productDetailViewModel.getProductDetails(this).observe(
                this, product -> {
                    assert product != null;
                    ImageUtil.setImage(activityProductDetailBinding.prodFullImage,
                            product.getImgUrl());
                    activityProductDetailBinding.setProductDetailVM(productDetailViewModel);

                getSupportActionBar().setTitle(product.getName());
                }
        );
    }
}

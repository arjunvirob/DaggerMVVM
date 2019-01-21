package com.myapps.daggermvvm.product_list;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapps.daggermvvm.product_details.ProductDetailActivity;
import com.myapps.daggermvvm.R;
import com.myapps.daggermvvm.databinding.ProductListItemBinding;
import com.myapps.daggermvvm.model.Product;
import com.myapps.daggermvvm.util.ImageUtil;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsHolder> {
    private List<Product> products;
    private Activity activity;

    public ProductsAdapter(List<Product> productList, Activity activity) {
        products = productList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ProductsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ProductListItemBinding productListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()), R.layout.product_list_item,
                viewGroup, false);

        return new ProductsHolder(productListItemBinding, new ProductItemViewModel());
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsHolder productsHolder, int i) {
        Product product = products.get(i);

        productsHolder.bindProducts(product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductsHolder extends RecyclerView.ViewHolder {
        private ProductListItemBinding productListItemBinding;
        private ProductItemViewModel productItemViewModel;

        public ProductsHolder(ProductListItemBinding productListItemBinding, ProductItemViewModel productItemViewModel) {
            super(productListItemBinding.getRoot());
            this.productListItemBinding = productListItemBinding;
            this.productItemViewModel = productItemViewModel;
        }

        private void bindProducts(Product product) {
            productItemViewModel.setProduct(product);
            productListItemBinding.setProductItemViewModel(productItemViewModel);
            productListItemBinding.executePendingBindings();

            ImageUtil.setImage(productListItemBinding.productIv, product.getImgUrl());

            productListItemBinding.productFullView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, ProductDetailActivity.class);
                    intent.putExtra("PRODUCT_ID", product.getId());
                    activity.startActivity(intent);
                }
            });
        }
    }
}

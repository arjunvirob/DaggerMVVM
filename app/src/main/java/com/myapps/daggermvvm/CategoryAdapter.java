package com.myapps.daggermvvm;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapps.daggermvvm.databinding.CategoryListItemBinding;
import com.myapps.daggermvvm.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {
    private Activity activity;
    private List<Category> categories;

    public CategoryAdapter(Activity activity, List<Category> categories) {
        this.activity = activity;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CategoryListItemBinding categoryListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()), R.layout.category_list_item, viewGroup, false
        );
        return new CategoryHolder(categoryListItemBinding, new CategoryItemViewModel());
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder categoryHolder, int i) {
        categoryHolder.bindView(categories.get(i));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class CategoryHolder extends RecyclerView.ViewHolder {
        private CategoryListItemBinding categoryListItemBinding;
        private CategoryItemViewModel categoryItemViewModel;

        public CategoryHolder(CategoryListItemBinding categoryListItemBinding,
                              CategoryItemViewModel categoryItemViewModel) {
            super(categoryListItemBinding.getRoot());
            this.categoryListItemBinding = categoryListItemBinding;
            this.categoryItemViewModel = categoryItemViewModel;
        }

        private void bindView(Category category) {
            categoryItemViewModel.setCategory(category);
            categoryListItemBinding.setCategoryItemViewModel(categoryItemViewModel);
            categoryListItemBinding.executePendingBindings();

            ImageUtil.setImage(
                    categoryListItemBinding.catImage,
                    category.getImage()
            );

            categoryListItemBinding.categoryCv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity,
                            ProductListActivity.class);
                    intent.putExtra("Category",
                            category.getCategoryId());
                    activity.startActivity(intent);
                }
            });
        }

    }
}

package com.myapps.daggermvvm.main_screen;

import android.arch.lifecycle.ViewModel;

import com.myapps.daggermvvm.model.Category;

public class CategoryItemViewModel extends ViewModel {
    private Category category;

    public CategoryItemViewModel() {
    }

    public CategoryItemViewModel(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

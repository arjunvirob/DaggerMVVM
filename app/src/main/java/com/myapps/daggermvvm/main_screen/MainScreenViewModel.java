package com.myapps.daggermvvm.main_screen;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.myapps.daggermvvm.model.Category;

public class MainScreenViewModel extends ViewModel {
    private ObservableList<Category> categories;

    public MainScreenViewModel() {
        categories = new ObservableArrayList<>();

        categories.add(new Category("Skin Care", "http://dfwstyledaily.com/wp-content/uploads/2017/09/0.png", "Skincare"));
        categories.add(new Category("Bath & Body", "https://adn-static2.nykaa.com/media/catalog/product/tr:h-800,w-800,cm-pad_resize/1/2/12678_s4_2.jpg", "Bath & Body"));
        categories.add(new Category("Nails", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKhGcjXWgMA0nwiUmlxrlAESlHgfL0bDec6YguvhJPL0jpxA1j", "Nails"));
        categories.add(new Category("Make Up", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR4XLkVG7jph691UiA54PFXc8nv-zpI2tOGCII4C88NW8r5U_A-Zw", "Makeup"));
        this.categories = categories;
    }

    public ObservableList<Category> getCategories() {
        return categories;
    }
}

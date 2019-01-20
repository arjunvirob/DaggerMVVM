package com.myapps.daggermvvm;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.myapps.daggermvvm.databinding.ActivityMainBinding;
import com.myapps.daggermvvm.model.Category;

public class MainActivity extends AppCompatActivity {
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = this;
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);

        activityMainBinding.categoryRv.setLayoutManager(new GridLayoutManager(activity, 2));
        activityMainBinding.categoryRv.setHasFixedSize(true);


        activityMainBinding.setMainScreenViewModel(new MainScreenViewModel());

        CategoryAdapter categoryAdapter = new CategoryAdapter(activity, activityMainBinding.getMainScreenViewModel().getCategories());
        activityMainBinding.categoryRv.setAdapter(categoryAdapter);
    }
}

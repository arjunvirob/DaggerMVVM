package com.myapps.daggermvvm.main_screen;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.widget.Toast;

import com.myapps.daggermvvm.AppTextService;
import com.myapps.daggermvvm.MyApp;
import com.myapps.daggermvvm.R;
import com.myapps.daggermvvm.data.ApiService;
import com.myapps.daggermvvm.databinding.ActivityMainBinding;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    private Activity activity;

    @Inject
    AppTextService appTextService;

    @Inject
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = this;
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);

        activityMainBinding.categoryRv.setLayoutManager(new GridLayoutManager(activity, 2));
        activityMainBinding.categoryRv.setHasFixedSize(true);

        MyApp.getAppComponent().inject(activity);

      //  Toast.makeText(activity, appTextService.getTextToDisplay(), Toast.LENGTH_SHORT).show();

        activityMainBinding.setMainScreenViewModel(new MainScreenViewModel());

        CategoryAdapter categoryAdapter = new CategoryAdapter(activity, activityMainBinding.getMainScreenViewModel().getCategories());
        activityMainBinding.categoryRv.setAdapter(categoryAdapter);
    }
}

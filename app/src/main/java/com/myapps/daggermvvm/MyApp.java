package com.myapps.daggermvvm;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

import javax.inject.Inject;

public class MyApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);

        context = this;
    }

    public static AppComponent getAppComponent() {
        return DaggerAppComponent.builder().build();
    }


}

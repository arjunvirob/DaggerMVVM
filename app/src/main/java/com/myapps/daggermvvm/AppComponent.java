package com.myapps.daggermvvm;

import android.app.Activity;

import dagger.Component;

@Component(modules = {MyModule.class})
public interface AppComponent {
    void inject(Activity activity);
}

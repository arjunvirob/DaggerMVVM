package com.myapps.daggermvvm;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MyModule {
    @Singleton
    @Provides
    public String getAppTextFromServer(AppTextServer appTextServer) {
        return appTextServer.getAppText();
    }

    @Provides
    @Singleton
    public AppTextServer getTextServer() {
        AppTextServer appTextServer = new AppTextServer();
        appTextServer.setAppText("Hello");
        return appTextServer;
    }


}

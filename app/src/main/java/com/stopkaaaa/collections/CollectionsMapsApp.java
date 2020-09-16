package com.stopkaaaa.collections;

import android.app.Application;

import androidx.annotation.VisibleForTesting;

import com.stopkaaaa.collections.di.component.AppComponent;
import com.stopkaaaa.collections.di.component.DaggerAppComponent;
import com.stopkaaaa.collections.di.module.AppModule;

public class CollectionsMapsApp extends Application {

    private static CollectionsMapsApp instance;
    private AppComponent appComponent;

    public static CollectionsMapsApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @VisibleForTesting
    public void setAppComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }
}

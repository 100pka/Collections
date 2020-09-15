package com.stopkaaaa.collections;

import android.app.Application;

import com.stopkaaaa.collections.di.component.AppComponent;
import com.stopkaaaa.collections.di.component.DaggerAppComponent;
import com.stopkaaaa.collections.di.module.ContextModule;

public class InitApplication extends Application {

    private static InitApplication instance;
    private AppComponent appComponent;

    public static InitApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public AppComponent component() {
        return appComponent;
    }
}

package com.stopkaaaa.collections;

import android.app.Application;
import android.content.Context;

import com.stopkaaaa.collections.di.component.AppComponent;
import com.stopkaaaa.collections.di.component.DaggerAppComponent;
import com.stopkaaaa.collections.di.module.AppModule;
import com.stopkaaaa.collections.di.module.ContextModule;

public class InitApplication extends Application {

    private AppComponent appComponent;

    public static InitApplication get(Context context) {
        return (InitApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .contextModule(new ContextModule(this))
                .build();
        appComponent.inject(this);
    }

    public AppComponent component() {
        return appComponent;
    }
}

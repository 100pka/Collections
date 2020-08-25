package com.stopkaaaa.collections.di.module;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.stopkaaaa.collections.InitApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private InitApplication initApplication;

    public AppModule(InitApplication initApplication) {
        this.initApplication = initApplication;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return initApplication;
    }
}

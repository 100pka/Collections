package com.stopkaaaa.collections.di.component;

import android.app.Application;
import android.content.Context;

import com.stopkaaaa.collections.InitApplication;
import com.stopkaaaa.collections.di.module.AppModule;
import com.stopkaaaa.collections.di.module.ContextModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ContextModule.class})
public interface AppComponent {

    void inject(InitApplication application);

    Context getContext();

}

package com.stopkaaaa.collections.di.component;

import android.content.Context;

import com.stopkaaaa.collections.InitApplication;
import com.stopkaaaa.collections.di.module.ContextModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class})
public interface AppComponent {

    Context getContext();

}

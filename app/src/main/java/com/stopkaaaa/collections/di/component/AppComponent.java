package com.stopkaaaa.collections.di.component;

import com.stopkaaaa.collections.di.module.AppModule;
import com.stopkaaaa.collections.di.module.FragmentModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void injectCalculatorsAndSuppliers(FragmentModule module);

}

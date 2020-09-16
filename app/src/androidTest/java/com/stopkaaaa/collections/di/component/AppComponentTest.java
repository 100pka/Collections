package com.stopkaaaa.collections.di.component;

import com.stopkaaaa.collections.di.module.AppModuleTest;
import com.stopkaaaa.collections.di.module.FragmentModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModuleTest.class)
public interface AppComponentTest extends AppComponent{

    void injectCalculatorsAndSuppliers(FragmentModule module);

}

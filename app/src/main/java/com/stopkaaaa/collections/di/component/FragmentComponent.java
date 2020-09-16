package com.stopkaaaa.collections.di.component;

import com.stopkaaaa.collections.di.FragmentScope;
import com.stopkaaaa.collections.di.module.FragmentModule;

import com.stopkaaaa.collections.ui.fragment.mapcollection.MapCollectionFragment;

import javax.inject.Singleton;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    void injectPresenter(MapCollectionFragment fragment);

}

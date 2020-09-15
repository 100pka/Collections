package com.stopkaaaa.collections.di.component;

import com.stopkaaaa.collections.base.BaseContract;
import com.stopkaaaa.collections.di.module.FragmentInjectorModuleTest;
import com.stopkaaaa.collections.di.scope.ActivityScope;
import com.stopkaaaa.collections.ui.fragment.mapcollection.MapCollectionFragmentTest;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = FragmentInjectorModuleTest.class)
public interface ActivityComponentTest{

    void inject(MapCollectionFragmentTest fragment);

    BaseContract.BasePresenter getPresenter();
}

package com.stopkaaaa.collections.di.component;

import android.content.Context;

import androidx.annotation.StringRes;

import com.stopkaaaa.collections.base.BaseContract;
import com.stopkaaaa.collections.di.module.FragmentInjectorModule;
import com.stopkaaaa.collections.di.scope.ActivityScope;
import com.stopkaaaa.collections.ui.fragment.mapcollection.MapCollectionFragment;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = FragmentInjectorModule.class)
public interface ActivityComponent {

    void inject(MapCollectionFragment fragment);

    BaseContract.BasePresenter getPresenter();
}

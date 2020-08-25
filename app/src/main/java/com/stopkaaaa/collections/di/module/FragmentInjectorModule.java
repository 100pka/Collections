package com.stopkaaaa.collections.di.module;

import android.content.Context;

import androidx.annotation.StringRes;

import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.base.BaseContract;
import com.stopkaaaa.collections.model.collections.CollectionCalculator;
import com.stopkaaaa.collections.model.collections.CollectionSupplier;
import com.stopkaaaa.collections.model.maps.MapCalculator;
import com.stopkaaaa.collections.model.maps.MapSupplier;
import com.stopkaaaa.collections.ui.fragment.mapcollection.MapCollectionPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentInjectorModule {

    private BaseContract.BaseView view;
    private int page;

    public FragmentInjectorModule(BaseContract.BaseView view, @StringRes int page) {
        this.view = view;
        this.page = page;
    }

    @Provides
    public BaseContract.BaseView provideView() {
        return view;
    }

    @Provides
    public BaseContract.BasePresenter providePresenter(BaseContract.BaseView fragmentContractView,
                                                          Context context) {
        if (page == R.string.collections) {
            return new MapCollectionPresenter(
                    fragmentContractView,
                    new CollectionSupplier(context),
                    new CollectionCalculator(context));
        }  if (page == R.string.maps) {
            return new MapCollectionPresenter(
                    fragmentContractView,
                    new MapSupplier(context),
                    new MapCalculator(context));
        }
        return null;
    }
}

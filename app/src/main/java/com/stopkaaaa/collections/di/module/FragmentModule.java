package com.stopkaaaa.collections.di.module;

import androidx.annotation.StringRes;

import com.stopkaaaa.collections.CollectionsMapsApp;
import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.base.BaseContract;
import com.stopkaaaa.collections.model.Calculator;
import com.stopkaaaa.collections.model.Supplier;
import com.stopkaaaa.collections.ui.fragment.mapcollection.MapCollectionPresenter;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private BaseContract.BaseView view;
    private int page;

    @Inject
    @Named("CollectionCalculator")
    Calculator collectionCalculator;

    @Inject
    @Named("MapCalculator")
    Calculator mapCalculator;

    @Inject
    @Named("CollectionSupplier")
    Supplier collectionSupplier;

    @Inject
    @Named("MapSupplier")
    Supplier mapSupplier;

    public FragmentModule(BaseContract.BaseView view, @StringRes int page) {
        CollectionsMapsApp.getInstance().getAppComponent().injectCalculatorsAndSuppliers(this);
        this.view = view;
        this.page = page;
    }

    @Provides
    public BaseContract.BaseView provideView() {
        return view;
    }

    @Provides
    public BaseContract.BasePresenter providePresenter() {
        if (page == R.string.collections) {
            return new MapCollectionPresenter(
                    view,
                    collectionSupplier,
                    collectionCalculator);
        }  if (page == R.string.maps) {
            return new MapCollectionPresenter(
                    view,
                    mapSupplier,
                    mapCalculator);
        }
        return null;
    }
}

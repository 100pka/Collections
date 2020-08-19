package com.stopkaaaa.collections.ui;

import android.content.Context;

import androidx.annotation.StringRes;

import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.base.BaseContract;
import com.stopkaaaa.collections.model.collections.CollectionCalculator;
import com.stopkaaaa.collections.model.collections.CollectionSupplier;
import com.stopkaaaa.collections.model.maps.MapCalculator;
import com.stopkaaaa.collections.model.maps.MapSupplier;
import com.stopkaaaa.collections.ui.fragment.mapcollection.MapCollectionPresenter;


public class FragmentInjector {

    public static BaseContract.BasePresenter getPresenter(BaseContract.BaseView fragmentContractView, Context context, @StringRes int page) {
        if (page == R.string.collections) {
            return new MapCollectionPresenter<CollectionSupplier, CollectionCalculator>(
                    fragmentContractView,
                    new CollectionSupplier(context),
                    new CollectionCalculator(context));
        }  if (page == R.string.maps) {
            return new MapCollectionPresenter<MapSupplier, MapCalculator>(
                    fragmentContractView,
                    new MapSupplier(context),
                    new MapCalculator(context));
        }
        return null;
    }
}

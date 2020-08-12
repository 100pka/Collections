package com.stopkaaaa.collections.ui;

import android.content.Context;

import com.stopkaaaa.collections.base.BaseContract;
import com.stopkaaaa.collections.model.collections.CollectionSupplier;
import com.stopkaaaa.collections.model.maps.MapSupplier;
import com.stopkaaaa.collections.ui.fragment.collections.CollectionsFragmentContract;
import com.stopkaaaa.collections.ui.fragment.collections.CollectionsFragmentPresenter;
import com.stopkaaaa.collections.ui.fragment.maps.MapsFragmentContract;
import com.stopkaaaa.collections.ui.fragment.maps.MapsFragmentPresenter;

public class FragmentInjector {

    public static <T> BaseContract.BasePresenter getPresenter(BaseContract.BaseView<T> fragmentContractView, Context context) {
        if (fragmentContractView instanceof CollectionsFragmentContract.View) {
            return new CollectionsFragmentPresenter((CollectionsFragmentContract.View) fragmentContractView, new CollectionSupplier(context));
        } else {
            return new MapsFragmentPresenter((MapsFragmentContract.View) fragmentContractView, new MapSupplier(context));
        }
    }
}

package com.stopkaaaa.collections.ui.fragment.maps;

import android.content.Context;

import com.stopkaaaa.collections.model.MapSupplier;

public class MapFragmentInjector {
        public static MapsFragmentPresenter getPresenter(MapsFragmentContract.View mapsFragmentContractView, Context context) {
            return new MapsFragmentPresenter(mapsFragmentContractView, MapSupplier.getInstance(context));
        }
}

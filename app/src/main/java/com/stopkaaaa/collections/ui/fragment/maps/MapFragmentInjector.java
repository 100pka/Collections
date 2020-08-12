package com.stopkaaaa.collections.ui.fragment.maps;

import android.content.Context;

import com.stopkaaaa.collections.model.maps.MapSupplier;

public class MapFragmentInjector {
        public static MapsFragmentPresenter getPresenter(MapsFragmentContract.View mapsFragmentContractView, Context context) {
            return new MapsFragmentPresenter(mapsFragmentContractView, new MapSupplier(context));
        }
}

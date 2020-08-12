package com.stopkaaaa.collections.ui.fragment.collections;

import android.content.Context;

import com.stopkaaaa.collections.model.collections.CollectionSupplier;

public class CollectionFragmentInjector {
    public static CollectionsFragmentPresenter getPresenter(CollectionsFragmentContract.View collectionsFragmentContractView, Context context) {
        return new CollectionsFragmentPresenter(collectionsFragmentContractView, new CollectionSupplier(context));
    }
}

package com.stopkaaaa.collections.di.module;

import android.content.Context;

import com.stopkaaaa.collections.model.Calculator;
import com.stopkaaaa.collections.model.Supplier;
import com.stopkaaaa.collections.model.collections.CollectionCalculator;
import com.stopkaaaa.collections.model.collections.CollectionSupplier;
import com.stopkaaaa.collections.model.maps.MapCalculator;
import com.stopkaaaa.collections.model.maps.MapSupplier;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Context context;

    private final Calculator collectionCalculator, mapCalculator;
    private final Supplier collectionSupplier, mapSupplier;

    public AppModule(Context app) {
        this.context = app;
        collectionCalculator = new CollectionCalculator(app);
        mapCalculator = new MapCalculator(app);
        collectionSupplier = new CollectionSupplier(app);
        mapSupplier = new MapSupplier(app);
    }

    @Provides
    @Singleton
    public Context getContext() { return context; }

    @Provides
    @Singleton
    @Named("CollectionCalculator")
    public Calculator getCollectionCalculator() { return collectionCalculator; }

    @Provides
    @Singleton
    @Named("MapCalculator")
    public Calculator getMapCalculator() { return mapCalculator; }

    @Provides
    @Singleton
    @Named("CollectionSupplier")
    public Supplier getCollectionSupplier() { return collectionSupplier; }

    @Provides
    @Singleton
    @Named("MapSupplier")
    public Supplier getMapSupplier() { return mapSupplier; }
}

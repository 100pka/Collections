package com.stopkaaaa.collections.di.module;

import android.content.Context;

import com.stopkaaaa.collections.model.Calculator;
import com.stopkaaaa.collections.model.Supplier;
import com.stopkaaaa.collections.model.collections.CollectionSupplier;
import com.stopkaaaa.collections.model.maps.MapSupplier;
import com.stopkaaaa.collections.model.CalculatorForTest;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModuleTest {
    private final Context context;

    private final Calculator calculatorForTest;
    private final Supplier collectionSupplier, mapSupplier;

    public AppModuleTest(Context app) {
        this.context = app;
        collectionSupplier = new CollectionSupplier(app);
        mapSupplier = new MapSupplier(app);
        calculatorForTest = new CalculatorForTest(app);
    }

    @Provides
    @Singleton
    public Context getContext() { return context; }

    @Provides
    @Singleton
    @Named("CollectionCalculator")
    public Calculator getCollectionCalculator() { return calculatorForTest; }

    @Provides
    @Singleton
    @Named("MapCalculator")
    public Calculator getMapCalculator() { return calculatorForTest; }

    @Provides
    @Singleton
    @Named("CollectionSupplier")
    public Supplier getCollectionSupplier() { return collectionSupplier; }

    @Provides
    @Singleton
    @Named("MapSupplier")
    public Supplier getMapSupplier() { return mapSupplier; }
}

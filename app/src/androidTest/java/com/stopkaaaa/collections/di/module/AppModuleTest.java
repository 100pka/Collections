package com.stopkaaaa.collections.di.module;

import android.content.Context;

import com.stopkaaaa.collections.CollectionsMapsApp;
import com.stopkaaaa.collections.model.Calculator;
import com.stopkaaaa.collections.model.CalculatorForTest;


public class AppModuleTest extends AppModule{

    private final Calculator calculatorForTest;

    public AppModuleTest(Context app) {
        super(app);
        calculatorForTest = new CalculatorForTest(CollectionsMapsApp.getInstance());
    }

    @Override
    public Calculator getCollectionCalculator() { return calculatorForTest; }

    @Override
    public Calculator getMapCalculator() { return calculatorForTest; }

}

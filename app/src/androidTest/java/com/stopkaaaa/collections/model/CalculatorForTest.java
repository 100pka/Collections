package com.stopkaaaa.collections.model;

import android.content.Context;

import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.dto.CalculationResultItem;
import com.stopkaaaa.collections.model.Calculator;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class CalculatorForTest implements Calculator {

    private Context context;

    public CalculatorForTest(Context context) {
        this.context = context;
    }

    @Override
    public CalculationResultItem calculate(CalculationResultItem item, int size){
        item.setTime(String.valueOf(size));
        long start = System.currentTimeMillis();
        long stop = start + 1000;
        while (start < stop) {
            start = System.currentTimeMillis();
        }
        return item;
    }
}

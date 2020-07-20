package com.stopkaaaa.collections.model;

import android.content.Context;

import com.stopkaaaa.collections.R;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;

public class MapCalculator implements Callable<String> {
    private final Map<Integer, Integer> map;
    private String operation;
    private String mapType;
    private Context context;

    public MapCalculator(int amount, Map<Integer, Integer> map, String operation, Context context) {
        this.operation = operation;
        this.map = map;
        this.mapType = map.getClass().getSimpleName();
        this.context = context;
        for (int i = 0; i < amount; i++) {
            map.put(i, i);
        }
    }

    @Override
    public String call() {
        long start, result;
        int rndIndex;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        rndIndex = new Random().nextInt(map.size());

        if (operation.equals(context.getString(R.string.addingTo))) {
            start = System.nanoTime();
            map.put(map.size() + 1, 0);
            result = System.nanoTime() - start;
            return mapType + "_" + operation + "_" + decimalFormat.format(result / 1000000.0);
        } else if (operation.equals(context.getString(R.string.searchInMap))) {
            start = System.nanoTime();
            map.get(rndIndex);
            result = System.nanoTime() - start;
            return mapType + "_" + operation + "_" + decimalFormat.format(result / 1000000.0);
        } else if (operation.equals(context.getString(R.string.removeFrom))) {
            start = System.nanoTime();
            map.remove(rndIndex);
            result = System.nanoTime() - start;
            return mapType + "_" + operation + "_" + decimalFormat.format(result / 1000000.0);
        } else return null;
    }
}

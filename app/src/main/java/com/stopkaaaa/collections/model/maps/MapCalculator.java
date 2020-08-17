package com.stopkaaaa.collections.model.maps;

import android.content.Context;

import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.dto.CalculationResultItem;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class MapCalculator{
    private int mapSize;
    private Context context;

    public MapCalculator(Context context) {
        this.context = context;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public synchronized String calculate(CalculationResultItem item) {
        String mapType = item.getListType();
        String operation = item.getOperation();
        Map<Integer, Integer> map = null;

        if (mapType.equals(context.getString(R.string.hashMap))) {
            map = new HashMap<>();
        } else {
            map = new TreeMap<>();
        }

        for (int i = 0; i < mapSize; i++) {
            map.put(i, i);
        }

        long start, result;
        StringBuilder resultStringBuilder = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        if (operation.equals(context.getString(R.string.addingTo))) {
            start = System.nanoTime();
            map.put(map.size() + 1, 0);
            result = System.nanoTime() - start;
            resultStringBuilder.append(decimalFormat.format(result / 1000000.0));
        } else if (operation.equals(context.getString(R.string.searchInMap))) {
            int rndIndex = new Random().nextInt(map.size());
            start = System.nanoTime();
            map.get(rndIndex);
            result = System.nanoTime() - start;
            resultStringBuilder.append(decimalFormat.format(result / 1000000.0));
        } else if (operation.equals(context.getString(R.string.removeFrom))) {
            int rndIndex = new Random().nextInt(map.size());
            start = System.nanoTime();
            map.remove(rndIndex);
            result = System.nanoTime() - start;
            resultStringBuilder.append(decimalFormat.format(result / 1000000.0));
        } else {
            return "";
        }
        return resultStringBuilder.toString();
    }
}

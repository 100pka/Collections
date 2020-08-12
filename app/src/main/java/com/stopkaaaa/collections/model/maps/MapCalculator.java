package com.stopkaaaa.collections.model.maps;

import android.content.Context;

import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.model.ModelContract;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class MapCalculator implements Runnable, ModelContract.Model {
    private Map<Integer, Integer> map;
    private String operation;
    private String mapType;
    private String resultString;
    private Context context;
    private ModelContract.ModelPresenter modelPresenter;

    public MapCalculator(int amount, String mapType, String operation, Context context,
                         ModelContract.ModelPresenter presenter) {
        this.operation = operation;
        this.mapType = mapType;
        this.context = context;
        this.modelPresenter = presenter;
        if (mapType.equals(context.getString(R.string.hashMap))) {
            map = new HashMap<>();
        } else if (mapType.equals(context.getString(R.string.treeMap))) {
            map = new TreeMap<>();
        }
        for (int i = 0; i < amount; i++) {
            map.put(i, i);
        }
    }

    @Override
    public void run() {
        if (calculation()) {
            modelPresenter.calculationFinished(mapType, operation, resultString);
        }
    }

    private boolean calculation() {
        long start, result;
        int rndIndex;
        StringBuilder resultStringBuilder = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        rndIndex = new Random().nextInt(map.size());

        if (operation.equals(context.getString(R.string.addingTo))) {
            start = System.nanoTime();
            map.put(map.size() + 1, 0);
            result = System.nanoTime() - start;
            resultStringBuilder.append(decimalFormat.format(result / 1000000.0));
        } else if (operation.equals(context.getString(R.string.searchInMap))) {
            start = System.nanoTime();
            map.get(rndIndex);
            result = System.nanoTime() - start;
            resultStringBuilder.append(decimalFormat.format(result / 1000000.0));
        } else if (operation.equals(context.getString(R.string.removeFrom))) {
            start = System.nanoTime();
            map.remove(rndIndex);
            result = System.nanoTime() - start;
            resultStringBuilder.append(decimalFormat.format(result / 1000000.0));
        } else {
            return false;
        }
        resultString = resultStringBuilder.toString();
        return true;
    }
}

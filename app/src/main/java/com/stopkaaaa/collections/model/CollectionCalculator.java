package com.stopkaaaa.collections.model;

import android.content.Context;

import com.stopkaaaa.collections.R;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;


public class CollectionCalculator implements Callable<String> {

    private final List<Integer> list;
    private String operation;
    private String listType;
    private Context context;

    public CollectionCalculator(int amount, List<Integer> list, String operation, Context context) {
        this.operation = operation;
        this.list = list;
        this.listType = list.getClass().getSimpleName();
        this.context = context;
        list.addAll(Collections.nCopies(amount, 0));
    }

    @Override
    public String call() {
        long start, result;
        int rndIndex;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        rndIndex = new Random().nextInt(list.size());

        if (operation.equals(context.getString(R.string.addingToStart))) {
            start = System.nanoTime();
            list.add(0, 99);
            result = System.nanoTime() - start;
            return listType + "_" + operation + "_" + decimalFormat.format(result / 1000000.0);
        } else if (operation.equals(context.getString(R.string.addingToMiddle))) {
            start = System.nanoTime();
            list.add(list.size() / 2, 99);
            result = System.nanoTime() - start;
            return listType + "_" + operation + "_" + decimalFormat.format(result / 1000000.0);
        } else if (operation.equals(context.getString(R.string.addingToEnd))) {
            start = System.nanoTime();
            list.add(list.size() - 1, 99);
            result = System.nanoTime() - start;
            return listType + "_" + operation + "_" + decimalFormat.format(result / 1000000.0);
        } else if (operation.equals(context.getString(R.string.searchIn))) {
            start = System.nanoTime();
            list.get(rndIndex);
            result = System.nanoTime() - start;
            return listType + "_" + operation + "_" + decimalFormat.format(result / 1000000.0);
        } else if (operation.equals(context.getString(R.string.removeFromStart))) {
            start = System.nanoTime();
            list.remove(0);
            result = System.nanoTime() - start;
            return listType + "_" + operation + "_" + decimalFormat.format(result / 1000000.0);
        } else if (operation.equals(context.getString(R.string.removeFromMiddle))) {
            start = System.nanoTime();
            list.remove(list.size() / 2);
            result = System.nanoTime() - start;
            return listType + "_" + operation + "_" + decimalFormat.format(result / 1000000.0);
        } else if (operation.equals(context.getString(R.string.removeFromEnd))) {
            start = System.nanoTime();
            list.remove(list.size() - 1);
            result = System.nanoTime() - start;
            return listType + "_" + operation + "_" + decimalFormat.format(result / 1000000.0);
        } else return null;
    }
}

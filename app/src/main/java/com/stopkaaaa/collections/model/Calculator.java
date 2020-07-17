package com.stopkaaaa.collections.model;

import android.content.Context;

import com.stopkaaaa.collections.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;

public class Calculator implements Callable<String> {

    private List<Integer> list;
    private Map<Integer, Integer> map;
    private int amount;
    private String operation;
    private String listType;
    private Context context;

    public Calculator(int amount, String listType, String operation, Context context) {
        this.amount = amount;
        this.operation = operation;
        this.listType = listType;
        this.context = context;
        listInit();
    }

    private void listInit() {
        if (listType.equals(context.getString(R.string.linkedList))) {
            list = new LinkedList<>();
            list.addAll(Collections.nCopies(amount, 0));
        } else if (listType.equals(context.getString(R.string.copyOnWriteArrayList))) {
            list = new CopyOnWriteArrayList<>();
            list.addAll(Collections.nCopies(amount, 0));
        } else if (listType.equals(context.getString(R.string.arrayList))) {
            list = new ArrayList<>();
            list.addAll(Collections.nCopies(amount, 0));
        } else if (listType.equals(context.getString(R.string.hashMap))) {
            map = new HashMap<>();
            for (int i = 0; i < amount; i++) {
                map.put(i, i);
            }
        } else if (listType.equals(context.getString(R.string.treeMap))) {
            map = new TreeMap<>();
            for (int i = 0; i < amount; i++) {
                map.put(i, i);
            }
        }
    }

    @Override
    public String call() {
        long start, result;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
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
            int rndIndex = new Random().nextInt(list.size());
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
        } else if (operation.equals(context.getString(R.string.addingTo))) {
            start = System.nanoTime();
            map.put(amount + 1, 0);
            result = System.nanoTime() - start;
            return listType + "_" + operation + "_" + decimalFormat.format(result / 1000000.0);
        } else if (operation.equals(context.getString(R.string.searchInMap))) {
            int rndIndex = new Random().nextInt(list.size());
            start = System.nanoTime();
            map.get(rndIndex);
            result = System.nanoTime() - start;
            return listType + "_" + operation + "_" + decimalFormat.format(result / 1000000.0);
        } else if (operation.equals(context.getString(R.string.removeFrom))) {
            int rndIndex = new Random().nextInt(list.size());
            start = System.nanoTime();
            map.remove(rndIndex);
            result = System.nanoTime() - start;
            return listType + "_" + operation + "_" + decimalFormat.format(result / 1000000.0);
        } else return null;
    }
}

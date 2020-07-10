package com.stopkaaaa.collections.model;

import android.content.Context;

import com.stopkaaaa.collections.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionsCalc implements Callable<String> {

    private List<Integer> list;
    private int amount;
    private String operation;
    private String listType;
    private Context context;

    public CollectionsCalc(int amount, String listType, String operation, Context context) {
        this.amount = amount;
        this.operation = operation;
        this.listType = listType;
        this.context = context;
        listInit();
    }

    private void listInit(){
        if (listType.equals(context.getString(R.string.linkedList))) {
            list = new LinkedList<>();
        } else if (listType.equals(context.getString(R.string.copyOnWriteArrayList))) {
            list = new CopyOnWriteArrayList<>();
        } else {
            list = new ArrayList<>();
        }
        list.addAll(Collections.nCopies(amount,0));
    }

    @Override
    public String call() {
        long start, result;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        switch (operation) {
            case ("Adding to start in"):
                start = System.nanoTime();
                list.add(0, 99);
                result = System.nanoTime() - start;
                return decimalFormat.format(result / 1000000.0);
            case ("Adding to middle in"):
                start = System.nanoTime();
                list.add(list.size()/2, 99);
                result = System.nanoTime() - start;
                return decimalFormat.format(result / 1000000.0);
            case ("Adding to end in"):
                start = System.nanoTime();
                list.add(list.size()-1, 99);
                result = System.nanoTime() - start;
                return decimalFormat.format(result / 1000000.0);
            case ("Search in"):
                start = System.nanoTime();
                int rndIndex = new Random().nextInt(list.size());
                list.get(rndIndex);
                result = System.nanoTime() - start;
                return decimalFormat.format(result / 1000000.0);
            case ("Remove from start in"):
                start = System.nanoTime();
                list.remove(0);
                result = System.nanoTime() - start;
                return decimalFormat.format(result / 1000000.0);
            case ("Remove from middle in"):
                start = System.nanoTime();
                list.remove(list.size()/2);
                result = System.nanoTime() - start;
                return decimalFormat.format(result / 1000000.0);
            case ("Remove from end in"):
                start = System.nanoTime();
                list.remove(list.size()-1);
                result = System.nanoTime() - start;
                return decimalFormat.format(result / 1000000.0);

            default:
                return null;
        }
    }
}

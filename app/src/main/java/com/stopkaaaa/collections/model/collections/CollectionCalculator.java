package com.stopkaaaa.collections.model.collections;

import android.content.Context;

import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.dto.CalculationResultItem;
import com.stopkaaaa.collections.model.Calculator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.inject.Inject;


public class CollectionCalculator implements Calculator {

    private Context context;

    @Inject
    public CollectionCalculator(Context context) {
        this.context = context;
    }

    @Override
    public CalculationResultItem calculate(CalculationResultItem item, int listSize) {
        String listType = item.getListType();
        String operation = item.getOperation();
        List<Integer> list;

        if (listType.equals(context.getString(R.string.linkedList))) {
            list = new LinkedList<>();
        } else if (listType.equals(context.getString(R.string.copyOnWriteArrayList))) {
            list = new CopyOnWriteArrayList<>();
        } else {
            list = new ArrayList<>();
        }
        list.addAll(Collections.nCopies(listSize, 0));

        long start, result;
        StringBuilder resultStringBuilder = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        if (operation.equals(context.getString(R.string.addingToStart))) {
            start = System.nanoTime();
            list.add(0, 99);
            result = System.nanoTime() - start;
            resultStringBuilder.append(decimalFormat.format(result / 1000000.0));
        } else if (operation.equals(context.getString(R.string.addingToMiddle))) {
            start = System.nanoTime();
            list.add(list.size() / 2, 99);
            result = System.nanoTime() - start;
            resultStringBuilder.append(decimalFormat.format(result / 1000000.0));
        } else if (operation.equals(context.getString(R.string.addingToEnd))) {
            start = System.nanoTime();
            list.add(list.size() - 1, 99);
            result = System.nanoTime() - start;
            resultStringBuilder.append(decimalFormat.format(result / 1000000.0));
        } else if (operation.equals(context.getString(R.string.searchIn))) {
            int rndIndex = new Random().nextInt(list.size());
            start = System.nanoTime();
            list.get(rndIndex);
            result = System.nanoTime() - start;
            resultStringBuilder.append(decimalFormat.format(result / 1000000.0));
        } else if (operation.equals(context.getString(R.string.removeFromStart))) {
            start = System.nanoTime();
            list.remove(0);
            result = System.nanoTime() - start;
            resultStringBuilder.append(decimalFormat.format(result / 1000000.0));
        } else if (operation.equals(context.getString(R.string.removeFromMiddle))) {
            start = System.nanoTime();
            list.remove(list.size() / 2);
            result = System.nanoTime() - start;
            resultStringBuilder.append(decimalFormat.format(result / 1000000.0));
        } else if (operation.equals(context.getString(R.string.removeFromEnd))) {
            start = System.nanoTime();
            list.remove(list.size() - 1);
            result = System.nanoTime() - start;
            resultStringBuilder.append(decimalFormat.format(result / 1000000.0));
        } else {
            return item;
        }
        item.setTime(resultStringBuilder.toString());
        return item;
    }
}

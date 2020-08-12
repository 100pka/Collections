package com.stopkaaaa.collections.model.collections;

import android.content.Context;
import android.util.Log;

import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.model.ModelContract;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;


public class CollectionCalculator implements Runnable, ModelContract.Model {
    private static final String TAG = "CollectionCalculator";
    private List<Integer> list;
    private String listType;
    private String operation;
    private String resultString;
    private Context context;
    private ModelContract.ModelPresenter modelPresenter;

    public CollectionCalculator(int amount, String listType, String operation, Context context,
                                ModelContract.ModelPresenter presenter) {
        this.operation = operation;
        this.context = context;
        this.listType = listType;
        this.modelPresenter = presenter;
        if (listType.equals(context.getString(R.string.linkedList))) {
            list = new LinkedList<>();
        } else if (listType.equals(context.getString(R.string.copyOnWriteArrayList))) {
            list = new CopyOnWriteArrayList<>();
        } else if (listType.equals(context.getString(R.string.arrayList))) {
            list = new ArrayList<>();
        }
        list.addAll(Collections.nCopies(amount, 0));
    }

    @Override
    public void run() {
        if (calculation()) {
            modelPresenter.calculationFinished(listType, operation, resultString);
        }
    }

    private boolean calculation() {
        Log.i(TAG, ": " + listType + " " + operation + " " + Thread.currentThread().getName());
        long start, result;
        int rndIndex;
        StringBuilder resultStringBuilder = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        rndIndex = new Random().nextInt(list.size());

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
            return false;
        }
        resultString = resultStringBuilder.toString();
        return true;
    }
}

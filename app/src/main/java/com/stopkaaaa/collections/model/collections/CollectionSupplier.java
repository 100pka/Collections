package com.stopkaaaa.collections.model.collections;


import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.dto.CalculationParameters;
import com.stopkaaaa.collections.dto.CalculationResultItem;
import com.stopkaaaa.collections.model.ModelContract;


import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CollectionSupplier implements ModelContract.Model, Runnable {

    public static final int SPAN_COUNT = 3;

    private static CollectionSupplier instance;

    private MutableLiveData<ArrayList<CalculationResultItem>> liveData = new MutableLiveData<>();
    private ArrayList<CalculationResultItem> listArrayList;
    private CalculationParameters calculationParameters;
    private Context context;

    private final BlockingQueue<Runnable> calculationQueue;
    private final ThreadPoolExecutor calculationThreadPool;

    private CollectionSupplier(Context context) {
        this.calculationParameters = new CalculationParameters("", "", false);
        this.context = context;
        this.calculationQueue = new LinkedBlockingQueue<Runnable>();
        this.calculationThreadPool = new ThreadPoolExecutor(1, 1,
                50, TimeUnit.MILLISECONDS, calculationQueue);
        init();
    }

    private void init() {
        listArrayList = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            StringBuilder operation = new StringBuilder();
            StringBuilder listType = new StringBuilder();

            if (i % 3 == 0) {
                listType.append(context.getString(R.string.arrayList));
            } else {
                if (i % 3 == 1) {
                    listType.append(context.getString(R.string.linkedList));
                } else listType.append(context.getString(R.string.copyOnWriteArrayList));
            }

            if (i < 3) {
                operation.append(context.getString(R.string.addingToStart));
            } else if (i > 2 && i < 6) {
                operation.append(context.getString(R.string.addingToMiddle));
            } else if (i > 5 && i < 9) {
                operation.append(context.getString(R.string.addingToEnd));
            } else if (i > 8 && i < 12) {
                operation.append(context.getString(R.string.searchIn));
            } else if (i > 11 && i < 15) {
                operation.append(context.getString(R.string.removeFromStart));
            } else if (i > 14 && i < 18) {
                operation.append(context.getString(R.string.removeFromMiddle));
            } else if (i > 17) {
                operation.append(context.getString(R.string.removeFromEnd));
            }

            CalculationResultItem calculationResultItem = new CalculationResultItem(listType.toString(), operation.toString());

            listArrayList.add(calculationResultItem);
            liveData.setValue(listArrayList);


        }
    }

    public void showProgress() {
        for (final CalculationResultItem calculationResultItem : listArrayList
        ) {
            calculationResultItem.setState(true);
        }
        liveData.postValue(listArrayList);
    }

    @Override
    public void run() {
        for (final CalculationResultItem calculationResultItem : listArrayList
        ) {
            startCalculationItem(calculationResultItem.getListType(), calculationResultItem.getOperation());
        }
    }

    public static synchronized CollectionSupplier getInstance(Context context) {
        if (instance == null) {
            instance = new CollectionSupplier(context);
        }
        return instance;
    }

    public boolean isCalculationFinished() {
        for (final CalculationResultItem calculationResultItem : listArrayList
        ) {
            if (calculationResultItem.getTime() == null) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<CalculationResultItem> getListArrayList() {
        return liveData.getValue();
    }

    public MutableLiveData<ArrayList<CalculationResultItem>> getData() {
        return liveData;
    }

    public static int getSpanCount() {
        return SPAN_COUNT;
    }

    public void setCalculationParameters(CalculationParameters calculationParameters) {
        this.calculationParameters = calculationParameters;
        calculationThreadPool.setCorePoolSize(calculationParameters.getThreads());
        calculationThreadPool.setMaximumPoolSize(calculationParameters.getThreads());
    }

    public synchronized void updateItem(String listType, String operation, String time) {
        for (final CalculationResultItem calculationResultItem : listArrayList
             ) {
            if (calculationResultItem.getListType().equals(listType) &&
            calculationResultItem.getOperation().equals(operation) &&
            (calculationResultItem.getTime() == null || calculationResultItem.getTime().isEmpty())) {
                calculationResultItem.setTime(time);
                calculationResultItem.setState(false);
            }
        }
        liveData.postValue(listArrayList);
    }
    private void startCalculationItem(String listType, String operation) {
        CollectionCalculator calculator = new CollectionCalculator(
                calculationParameters.getAmount(), listType, operation, context);
        calculationThreadPool.execute(calculator);
    }

    @Override
    public void startCalculation() {
        new Thread(this).start();
    }
}

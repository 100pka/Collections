package com.stopkaaaa.collections.model.maps;

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

public class MapSupplier implements ModelContract.Model, Runnable {
    public static final int SPAN_COUNT = 2;
    private static MapSupplier instance;

    private MutableLiveData<ArrayList<CalculationResultItem>> liveData = new MutableLiveData<>();
    private ArrayList<CalculationResultItem> listArrayList;
    private CalculationParameters calculationParameters;
    private Context context;

    private final BlockingQueue<Runnable> calculationQueue;
    private final ThreadPoolExecutor calculationThreadPool;

    private MapSupplier(Context context) {
        this.calculationParameters = new CalculationParameters("", "", false);
        this.context = context;
        this.calculationQueue = new LinkedBlockingQueue<Runnable>();
        this.calculationThreadPool = new ThreadPoolExecutor(1, 1,
                50, TimeUnit.MILLISECONDS, calculationQueue);
        init();
    }

    private void init() {
        listArrayList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            StringBuilder operation = new StringBuilder();
            StringBuilder mapType = new StringBuilder();

            if (i % 2 == 0) {
                mapType.append(context.getString(R.string.hashMap));
            } else {
                mapType.append(context.getString(R.string.treeMap));
            }

            if (i < 2) {
                operation.append(context.getString(R.string.addingTo));
            } else if (i > 1 && i < 4) {
                operation.append(context.getString(R.string.searchInMap));
            } else {
                operation.append(context.getString(R.string.removeFrom));
            }

            CalculationResultItem calculationResultItem = new CalculationResultItem(mapType.toString(), operation.toString());

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

    public static synchronized MapSupplier getInstance(Context context) {
        if (instance == null) {
            instance = new MapSupplier(context);
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

    public void setCalculationParameters(CalculationParameters calculationParameters) {
        this.calculationParameters = calculationParameters;
        calculationThreadPool.setCorePoolSize(calculationParameters.getThreads());
        calculationThreadPool.setMaximumPoolSize(calculationParameters.getThreads());
    }

    public MutableLiveData<ArrayList<CalculationResultItem>> getData() {
        return liveData;
    }

    public static int getSpanCount() {
        return SPAN_COUNT;
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
    public void startCalculationItem(String mapType, String operation) {
        MapCalculator calculator = new MapCalculator(
                calculationParameters.getAmount(), mapType, operation, context);
        calculationThreadPool.execute(calculator);
    }

    @Override
    public void startCalculation() {
        new Thread(this).start();
    }
}

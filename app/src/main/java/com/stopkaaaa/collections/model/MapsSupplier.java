package com.stopkaaaa.collections.model;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.google.common.util.concurrent.ListenableFutureTask;
import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.dto.CalculationParameters;
import com.stopkaaaa.collections.dto.CalculationResultItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapsSupplier implements ModelContract.Model{
    private static MapsSupplier instance;

    private MutableLiveData<ArrayList<CalculationResultItem>> liveData = new MutableLiveData<>();

    private ArrayList<CalculationResultItem> listArrayList;

    private CalculationParameters calculationParameters;

    private Context context;

    private MapsSupplier(Context context) {
        this.calculationParameters = new CalculationParameters("", "", false);
        this.context = context;
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

    @Override
    public void calculation() {
        final ExecutorService executor = Executors.newFixedThreadPool(calculationParameters.getThreads());
        final ExecutorService listenersExecutor = Executors.newFixedThreadPool(calculationParameters.getThreads());

        if (calculationParameters == null) {
            return;
        }

        for (final CalculationResultItem calculationResultItem : listArrayList
        ) {
            calculationResultItem.setState(true);
        }
        liveData.setValue(listArrayList);
        Map<Integer, Integer> map = null;

        for (final CalculationResultItem calculationResultItem : listArrayList
        ) {
            String mapType = calculationResultItem.getListType();
            if (mapType.equals(context.getString(R.string.hashMap))) {
                map = new HashMap<>();
            } else if (mapType.equals(context.getString(R.string.treeMap))) {
                map = new TreeMap<>();
            }

            final ListenableFutureTask<String> task = ListenableFutureTask.create(new MapCalculator(
                    calculationParameters.getAmount(), map,
                    calculationResultItem.getOperation(), context));
            task.addListener(new Runnable() {
                @Override
                public void run() {
                    try {
                        String[] result = task.get().split("_");
                        for (final CalculationResultItem calculationResultItem : listArrayList
                        ) {
                            if (calculationResultItem.getListType().equals(result[0]) &&
                                    calculationResultItem.getOperation().equals(result[1])) {
                                calculationResultItem.setTime(result[2]);
                                calculationResultItem.setState(false);
                                liveData.postValue(listArrayList);
                            }
                        }
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (isCalculationFinished()) {
                        executor.shutdown();
                        listenersExecutor.shutdown();
                    }
                }
            }, listenersExecutor);

            executor.execute(task);
        }
    }


    public static synchronized MapsSupplier getInstance(Context context) {
        if (instance == null) {
            instance = new MapsSupplier(context);
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
    }

    public MutableLiveData<ArrayList<CalculationResultItem>> getData() {
        return liveData;
    }
}

package com.stopkaaaa.collections.model;


import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.google.common.util.concurrent.ListenableFutureTask;
import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.dto.CalculationParameters;
import com.stopkaaaa.collections.dto.CalculationResultItem;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CollectionSupplier implements ModelContract.Model {

    private static CollectionSupplier instance;

    private MutableLiveData<ArrayList<CalculationResultItem>> liveData = new MutableLiveData<>();

    private ArrayList<CalculationResultItem> listArrayList;

    private CalculationParameters calculationParameters;

    private Context context;

    private CollectionSupplier(Context context) {
        this.calculationParameters = new CalculationParameters("", "", false);
        this.context = context;
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

        for (final CalculationResultItem calculationResultItem : listArrayList
        ) {
            final ListenableFutureTask<String> task = ListenableFutureTask.create(new Calculator(
                    calculationParameters.getAmount(),
                    calculationResultItem.getListType(), calculationResultItem.getOperation(), context));
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

    public void setCalculationParameters(CalculationParameters calculationParameters) {
        this.calculationParameters = calculationParameters;
    }

    public MutableLiveData<ArrayList<CalculationResultItem>> getData() {
        return liveData;
    }
}

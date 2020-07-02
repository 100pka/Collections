package com.stopkaaaa.collections.model;


import android.content.Context;

import com.google.common.util.concurrent.ListenableFutureTask;
import com.stopkaaaa.collections.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CollectionsResult implements ModelContract.Model{

    private static CollectionsResult instance;

    private ArrayList<ListModel> listArrayList;

    private CalculationParameters calculationParameters;

    private ModelContract.ModelPresenter modelPresenter;

    private Context context;

    private CollectionsResult(ModelContract.ModelPresenter modelPresenter, Context context) {
        this.calculationParameters = new CalculationParameters("", "", false);
        this.modelPresenter = modelPresenter;
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

            if (i < 3) operation.append(context.getString(R.string.addingToStart));
            if (i > 2 && i < 6) operation.append(context.getString(R.string.addingToMiddle));
            if (i > 5 && i < 9) operation.append(context.getString(R.string.addingToEnd));
            if (i > 8 && i < 12) operation.append(context.getString(R.string.searchIn));
            if (i > 11 && i < 15) operation.append(context.getString(R.string.removeFromStart));
            if (i > 14 && i < 18) operation.append(context.getString(R.string.removeFromMiddle));
            if (i > 17) operation.append(context.getString(R.string.removeFromEnd));

            ListModel listModel = new ListModel(listType.toString(), operation.toString());

            listArrayList.add(listModel);

        }
    }

    @Override
    public void calculation() {
        ExecutorService executor = Executors
                .newFixedThreadPool(Integer.parseInt(calculationParameters.getThreads()));
        if (calculationParameters == null) return;
        for (final ListModel listModel: listArrayList
             ) {
            ListenableFutureTask<String> task = ListenableFutureTask.create(new CollectionsCalc(
                    Integer.parseInt(calculationParameters.getAmount()),
                    listModel.getListType(), listModel.getOperation()));
            listModel.setTask(task);
            listModel.getTask().addListener(new Runnable() {
                @Override
                public void run() {
                    try {
                        listModel.setTime(listModel.getTask().get());
                        modelPresenter.notifyRecyclerAdapter();
                    }
                    catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, executor);
            executor.execute(listModel.getTask());
        }
    }


    public static synchronized CollectionsResult getInstance(ModelContract.ModelPresenter modelPresenter, Context context) {
        if (instance == null) {
            instance = new CollectionsResult(modelPresenter, context);
        }
        return instance;
    }

    public ArrayList<ListModel> getListArrayList() {
        return listArrayList;
    }

    public void setListArrayList(ArrayList<ListModel> listArrayList) {
        this.listArrayList = listArrayList;
    }

    public CalculationParameters getCalculationParameters() {
        return calculationParameters;
    }

    public void setCalculationParameters(CalculationParameters calculationParameters) {
        this.calculationParameters = calculationParameters;
    }
}

package com.stopkaaaa.collections.model.collections;

import android.content.Context;

import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.dto.CalculationResultItem;
import com.stopkaaaa.collections.model.ModelContract;

import java.util.ArrayList;
import java.util.List;

public class CollectionSupplier {

    public static final int SPAN_COUNT = 3;

    private List<CalculationResultItem> listArrayList;
    private List<CollectionCalculator> taskList;
    private Context context;


    public CollectionSupplier(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        listArrayList = new ArrayList<>();

        listArrayList.add(new CalculationResultItem(context.getString(R.string.arrayList), context.getString(R.string.addingToStart)));
        listArrayList.add(new CalculationResultItem(context.getString(R.string.linkedList), context.getString(R.string.addingToStart)));
        listArrayList.add(new CalculationResultItem(context.getString(R.string.copyOnWriteArrayList), context.getString(R.string.addingToStart)));

        listArrayList.add(new CalculationResultItem(context.getString(R.string.arrayList), context.getString(R.string.addingToMiddle)));
        listArrayList.add(new CalculationResultItem(context.getString(R.string.linkedList), context.getString(R.string.addingToMiddle)));
        listArrayList.add(new CalculationResultItem(context.getString(R.string.copyOnWriteArrayList), context.getString(R.string.addingToMiddle)));

        listArrayList.add(new CalculationResultItem(context.getString(R.string.arrayList), context.getString(R.string.addingToEnd)));
        listArrayList.add(new CalculationResultItem(context.getString(R.string.linkedList), context.getString(R.string.addingToEnd)));
        listArrayList.add(new CalculationResultItem(context.getString(R.string.copyOnWriteArrayList), context.getString(R.string.addingToEnd)));

        listArrayList.add(new CalculationResultItem(context.getString(R.string.arrayList), context.getString(R.string.searchIn)));
        listArrayList.add(new CalculationResultItem(context.getString(R.string.linkedList), context.getString(R.string.searchIn)));
        listArrayList.add(new CalculationResultItem(context.getString(R.string.copyOnWriteArrayList), context.getString(R.string.searchIn)));

        listArrayList.add(new CalculationResultItem(context.getString(R.string.arrayList), context.getString(R.string.removeFromStart)));
        listArrayList.add(new CalculationResultItem(context.getString(R.string.linkedList), context.getString(R.string.removeFromStart)));
        listArrayList.add(new CalculationResultItem(context.getString(R.string.copyOnWriteArrayList), context.getString(R.string.removeFromStart)));

        listArrayList.add(new CalculationResultItem(context.getString(R.string.arrayList), context.getString(R.string.removeFromMiddle)));
        listArrayList.add(new CalculationResultItem(context.getString(R.string.linkedList), context.getString(R.string.removeFromMiddle)));
        listArrayList.add(new CalculationResultItem(context.getString(R.string.copyOnWriteArrayList), context.getString(R.string.removeFromMiddle)));

        listArrayList.add(new CalculationResultItem(context.getString(R.string.arrayList), context.getString(R.string.removeFromEnd)));
        listArrayList.add(new CalculationResultItem(context.getString(R.string.linkedList), context.getString(R.string.removeFromEnd)));
        listArrayList.add(new CalculationResultItem(context.getString(R.string.copyOnWriteArrayList), context.getString(R.string.removeFromEnd)));
    }

    public List<CalculationResultItem> getRecyclerData() {
        return listArrayList;
    }

    public List<CollectionCalculator> getTasks(int amount, ModelContract.ModelPresenter presenter) {
        taskList = new ArrayList<>();
        for (CalculationResultItem item: listArrayList) {
            CollectionCalculator task = new CollectionCalculator(amount, item.getListType(),
                    item.getOperation(), context, presenter);
            taskList.add(task);
        }
        return taskList;
    }

    public int getSpanCount() {
        return SPAN_COUNT;
    }
}

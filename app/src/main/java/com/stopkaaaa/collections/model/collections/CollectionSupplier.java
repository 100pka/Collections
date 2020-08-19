package com.stopkaaaa.collections.model.collections;

import android.content.Context;

import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.dto.CalculationResultItem;
import com.stopkaaaa.collections.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class CollectionSupplier implements Supplier {

    public static final int SPAN_COUNT = 3;

    private Context context;


    public CollectionSupplier(Context context) {
        this.context = context;
    }

    @Override
    public List<CalculationResultItem> getTaskList() {
        List<CalculationResultItem> taskList = new ArrayList<>();

        taskList.add(new CalculationResultItem(context.getString(R.string.arrayList), context.getString(R.string.addingToStart)));
        taskList.add(new CalculationResultItem(context.getString(R.string.linkedList), context.getString(R.string.addingToStart)));
        taskList.add(new CalculationResultItem(context.getString(R.string.copyOnWriteArrayList), context.getString(R.string.addingToStart)));

        taskList.add(new CalculationResultItem(context.getString(R.string.arrayList), context.getString(R.string.addingToMiddle)));
        taskList.add(new CalculationResultItem(context.getString(R.string.linkedList), context.getString(R.string.addingToMiddle)));
        taskList.add(new CalculationResultItem(context.getString(R.string.copyOnWriteArrayList), context.getString(R.string.addingToMiddle)));

        taskList.add(new CalculationResultItem(context.getString(R.string.arrayList), context.getString(R.string.addingToEnd)));
        taskList.add(new CalculationResultItem(context.getString(R.string.linkedList), context.getString(R.string.addingToEnd)));
        taskList.add(new CalculationResultItem(context.getString(R.string.copyOnWriteArrayList), context.getString(R.string.addingToEnd)));

        taskList.add(new CalculationResultItem(context.getString(R.string.arrayList), context.getString(R.string.searchIn)));
        taskList.add(new CalculationResultItem(context.getString(R.string.linkedList), context.getString(R.string.searchIn)));
        taskList.add(new CalculationResultItem(context.getString(R.string.copyOnWriteArrayList), context.getString(R.string.searchIn)));

        taskList.add(new CalculationResultItem(context.getString(R.string.arrayList), context.getString(R.string.removeFromStart)));
        taskList.add(new CalculationResultItem(context.getString(R.string.linkedList), context.getString(R.string.removeFromStart)));
        taskList.add(new CalculationResultItem(context.getString(R.string.copyOnWriteArrayList), context.getString(R.string.removeFromStart)));

        taskList.add(new CalculationResultItem(context.getString(R.string.arrayList), context.getString(R.string.removeFromMiddle)));
        taskList.add(new CalculationResultItem(context.getString(R.string.linkedList), context.getString(R.string.removeFromMiddle)));
        taskList.add(new CalculationResultItem(context.getString(R.string.copyOnWriteArrayList), context.getString(R.string.removeFromMiddle)));

        taskList.add(new CalculationResultItem(context.getString(R.string.arrayList), context.getString(R.string.removeFromEnd)));
        taskList.add(new CalculationResultItem(context.getString(R.string.linkedList), context.getString(R.string.removeFromEnd)));
        taskList.add(new CalculationResultItem(context.getString(R.string.copyOnWriteArrayList), context.getString(R.string.removeFromEnd)));

        return taskList;
    }

    @Override
    public int getSpanCount() {
        return SPAN_COUNT;
    }
}

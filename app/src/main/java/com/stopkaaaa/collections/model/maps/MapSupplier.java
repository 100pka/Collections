package com.stopkaaaa.collections.model.maps;

import android.content.Context;

import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.dto.CalculationResultItem;

import java.util.ArrayList;
import java.util.List;

public class MapSupplier {

    public static final int SPAN_COUNT = 2;

    private Context context;

    public MapSupplier(Context context) {
        this.context = context;
    }

    public List<CalculationResultItem> getTaskList() {
        List<CalculationResultItem> taskList = new ArrayList<>();

        taskList.add(new CalculationResultItem(context.getString(R.string.hashMap), context.getString(R.string.addingTo)));
        taskList.add(new CalculationResultItem(context.getString(R.string.treeMap), context.getString(R.string.addingTo)));

        taskList.add(new CalculationResultItem(context.getString(R.string.hashMap), context.getString(R.string.searchInMap)));
        taskList.add(new CalculationResultItem(context.getString(R.string.treeMap), context.getString(R.string.searchInMap)));

        taskList.add(new CalculationResultItem(context.getString(R.string.hashMap), context.getString(R.string.removeFrom)));
        taskList.add(new CalculationResultItem(context.getString(R.string.treeMap), context.getString(R.string.removeFrom)));

        return taskList;
    }

    public int getSpanCount() {
        return SPAN_COUNT;
    }
}

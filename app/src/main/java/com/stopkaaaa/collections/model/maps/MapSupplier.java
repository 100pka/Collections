package com.stopkaaaa.collections.model.maps;

import android.content.Context;

import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.dto.CalculationResultItem;

import java.util.ArrayList;
import java.util.List;

public class MapSupplier {

    public static final int SPAN_COUNT = 2;

    private ArrayList<CalculationResultItem> listArrayList;
    private Context context;

    public MapSupplier(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        listArrayList = new ArrayList<>();

        listArrayList.add(new CalculationResultItem(context.getString(R.string.hashMap), context.getString(R.string.addingTo)));
        listArrayList.add(new CalculationResultItem(context.getString(R.string.treeMap), context.getString(R.string.addingTo)));

        listArrayList.add(new CalculationResultItem(context.getString(R.string.hashMap), context.getString(R.string.searchInMap)));
        listArrayList.add(new CalculationResultItem(context.getString(R.string.treeMap), context.getString(R.string.searchInMap)));

        listArrayList.add(new CalculationResultItem(context.getString(R.string.hashMap), context.getString(R.string.removeFrom)));
        listArrayList.add(new CalculationResultItem(context.getString(R.string.treeMap), context.getString(R.string.removeFrom)));
    }

    public List<CalculationResultItem> getRecyclerData() {
        return listArrayList;
    }

    public int getSpanCount() {
        return SPAN_COUNT;
    }

    public Context getContext() {
        return context;
    }
}

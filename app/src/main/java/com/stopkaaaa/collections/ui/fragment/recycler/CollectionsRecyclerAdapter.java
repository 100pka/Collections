package com.stopkaaaa.collections.ui.fragment.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.dto.CalculationResultItem;

import java.util.ArrayList;
import java.util.List;

public class CollectionsRecyclerAdapter extends RecyclerView.Adapter<ResultViewHolder> {

    private final List<CalculationResultItem> calculationResultItems = new ArrayList<>();

    public CollectionsRecyclerAdapter() {
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calculation_result, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        holder.bindItem(calculationResultItems.get(position));
    }

    @Override
    public int getItemCount() {
        return calculationResultItems.size();
    }

    public void setItems(List<CalculationResultItem> calculationResultItems) {
        this.calculationResultItems.clear();
        this.calculationResultItems.addAll(calculationResultItems);
        notifyDataSetChanged();
    }

    public void updateItem(int itemIndex, String time) {
        calculationResultItems.get(itemIndex).setTime(time);
        calculationResultItems.get(itemIndex).setState(false);
        notifyItemChanged(itemIndex);
    }

    public void showProgress(boolean showProgress) {
        for (CalculationResultItem item: calculationResultItems
             ) {
            item.setTime("0");
            item.setState(showProgress);
        }
        notifyDataSetChanged();
    }
}

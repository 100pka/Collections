package com.stopkaaaa.collections.ui.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.model.CalculationResult;


import java.util.ArrayList;
import java.util.List;

public class CollectionsRecyclerAdapter extends RecyclerView.Adapter<ResultViewHolder> {

    private final List<CalculationResult> calculationResults = new ArrayList<>();

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
        holder.bindItem(calculationResults.get(position));
        if (calculationResults.get(position).isState()) {
            holder.progressBar.setVisibility(View.VISIBLE);
        }
        holder.progressBar.animate().alpha(calculationResults.get(position).isState()?1:0).start();
        if (!calculationResults.get(position).getTime().equals("0")) {
            holder.progressBar.setVisibility(View.INVISIBLE);
            holder.setItemTime(calculationResults.get(position).getTime() + " ms");
        }
    }

    @Override
    public int getItemCount() {
        return calculationResults.size();
    }

    public void setItems(List<CalculationResult> calculationResults){
        this.calculationResults.clear();
        this.calculationResults.addAll(calculationResults);
        this.notifyDataSetChanged();
    }
}

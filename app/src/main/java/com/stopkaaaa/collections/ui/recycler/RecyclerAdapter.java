package com.stopkaaaa.collections.ui.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.model.CalculationResult;


import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<ResultViewHolder> {

    private List<CalculationResult> calculationResults;

    public RecyclerAdapter(List<CalculationResult> calculationResults) {
        this.calculationResults = calculationResults;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calculation_result, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        holder.setItemName(calculationResults.get(position).getTitle());
        if (CalculationResult.isStartButtonClicked()) {
            holder.setStartButtonClicked();
        }
        if (!calculationResults.get(position).getTime().equals("0 ms")) {
            holder.setItemTime(calculationResults.get(position).getTime());
        }
    }

    @Override
    public int getItemCount() {
        return calculationResults.size();
    }

    public void setCalculationResults(List<CalculationResult> calculationResults){
        this.calculationResults = calculationResults;
        this.notifyDataSetChanged();
    }
}

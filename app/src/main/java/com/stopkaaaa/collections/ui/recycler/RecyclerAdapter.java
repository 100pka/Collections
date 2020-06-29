package com.stopkaaaa.collections.ui.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.model.ListModel;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<ResultViewHolder> {

    private final ResultsListPresenter presenter;

    public RecyclerAdapter(ResultsListPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calculation_result, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        presenter.onBindResultAtPosition(position, holder);
    }

    @Override
    public int getItemCount() {

        return presenter.getCalculationResultCount();
    }

    public ResultsListPresenter getPresenter() {
        return presenter;
    }
}
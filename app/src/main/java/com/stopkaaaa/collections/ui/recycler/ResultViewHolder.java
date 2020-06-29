package com.stopkaaaa.collections.ui.recycler;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stopkaaaa.collections.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultViewHolder extends RecyclerView.ViewHolder implements RecyclerItemView{
    @BindView(R.id.itemName)
    TextView itemNameTextView;
    @BindView(R.id.itemTime)
    TextView itemTimeTextView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public ResultViewHolder(@NonNull View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    @Override
    public void setItemName(String title) {
        itemNameTextView.setText(title);
    }

    @Override
    public void setItemTime(String time) {
        itemTimeTextView.setText(time);
        progressBar.setVisibility(View.GONE);
        itemTimeTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setStartButtonClicked() {
        progressBar.setVisibility(View.VISIBLE);
    }
}

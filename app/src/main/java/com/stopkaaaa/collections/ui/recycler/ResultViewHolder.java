package com.stopkaaaa.collections.ui.recycler;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.model.CalculationResult;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultViewHolder extends RecyclerView.ViewHolder{
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

    public void bindItem(CalculationResult item) {
        itemNameTextView.setText(item.getTitle());
        if (item.isState()) {
            progressBar.setVisibility(View.VISIBLE);
        }
        progressBar.animate().alpha(item.isState()?1:0).start();
        if (!item.getTime().equals("0")) {
            progressBar.setVisibility(View.INVISIBLE);
            itemTimeTextView.setText(item.getTime() + " ms");
        }
    }
}

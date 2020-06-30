package com.stopkaaaa.collections.ui.recycler;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stopkaaaa.collections.R;

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

    public void setItemName(String title) {
        itemNameTextView.setText(title);
    }

    public void setItemTime(String time) {
        itemTimeTextView.setText(time);
    //    progressBar.setVisibility(View.INVISIBLE);
        itemTimeTextView.setVisibility(View.VISIBLE);
    }

    public void setStartButtonClicked() {
        progressBar.setVisibility(View.VISIBLE);
        ObjectAnimator progressAnimator;
        progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0,1);
        progressAnimator.setDuration(7000);
        progressAnimator.start();
    }
}

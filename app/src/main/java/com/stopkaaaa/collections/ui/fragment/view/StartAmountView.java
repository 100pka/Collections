package com.stopkaaaa.collections.ui;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.dto.CalculationParameters;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartAmountView extends ConstraintLayout {
    @BindView(R.id.startButton)
    ToggleButton startButton;
    @BindView(R.id.elementsAmount)
    EditText elementsAmountEditText;
    @BindView(R.id.threadsAmount)
    EditText threadsAmountEditText;

    public StartAmountView(Context context) {
        super(context, null);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_view_start_amount, this);
        ButterKnife.bind(this);
    }

    public StartAmountView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_view_start_amount, this);
        ButterKnife.bind(this);
    }

    public CalculationParameters getCalculationData() {
        return new CalculationParameters(this.getElementsAmount(),
                this.getThreadsAmount(),
                this.startButtonIsChecked());
    }

    public String getElementsAmount() {
        if (TextUtils.isEmpty(getText(elementsAmountEditText.getText()))) {
            return "";
        }
        return getText(elementsAmountEditText.getText());
    }

    public String getThreadsAmount() {
        if (TextUtils.isEmpty(getText(threadsAmountEditText.getText()))) {
            return "";
        }
        return getText(threadsAmountEditText.getText());
    }

    public void setOnStartCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        startButton.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    public boolean startButtonIsChecked() {
        return startButton.isChecked();
    }

    public void uncheckStartButton() {
        startButton.setChecked(false);
    }

    public String getText(Editable editText) {
        return editText.toString();
    }

}
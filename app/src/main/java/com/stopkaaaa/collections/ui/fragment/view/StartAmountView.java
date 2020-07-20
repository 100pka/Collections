package com.stopkaaaa.collections.ui.fragment.view;

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
        this(context, null);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_view_start_amount, this);
        ButterKnife.bind(this);
    }

    public StartAmountView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_view_start_amount, this);
        ButterKnife.bind(this);
    }

    public StartAmountView(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
    }

    public CalculationParameters getCalculationData() {
        return new CalculationParameters(this.getElementsAmount(),
                this.getThreadsAmount(),
                this.startButtonIsChecked());
    }

    public String getElementsAmount() {
        if (TextUtils.isEmpty(getText(elementsAmountEditText))) {
            return "";
        }
        return getText(elementsAmountEditText);
    }

    public String getThreadsAmount() {
        if (TextUtils.isEmpty(getText(threadsAmountEditText))) {
            return "";
        }
        return getText(threadsAmountEditText);
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

    public String getText(EditText editText) {
        return editText.getText().toString();
    }

}
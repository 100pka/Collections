package com.stopkaaaa.collections.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ToggleButton;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.model.CalculationParameters;

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
        initializeViews(context);
    }

    public StartAmountView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        initializeViews(context);
    }

    private void initializeViews(Context context) {
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
        if (TextUtils.isEmpty(elementsAmountEditText.getText().toString())){
            return "";
        }
        return elementsAmountEditText.getText().toString();
    }

    public String getThreadsAmount() {
        if (TextUtils.isEmpty(elementsAmountEditText.getText().toString())){
            return "";
        }
        return threadsAmountEditText.getText().toString();
    }

    public void setOnStartButtonClickListener(OnClickListener onClickListener) {
        startButton.setOnClickListener(onClickListener);
    }

    public boolean startButtonIsChecked() {
        return startButton.isChecked();
    }

}
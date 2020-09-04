package com.stopkaaaa.collections.dto;

import android.text.TextUtils;
import android.util.Log;

public class CalculationParameters {
    private final String amount;
    private final String threads;
    private final boolean checked;

    public CalculationParameters(String amount, String threads, boolean checked) {
        this.amount = amount;
        this.threads = threads;
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public boolean isThreadsValid () {
        return !threads.isEmpty() && TextUtils.isDigitsOnly(threads)
                && threads.matches("^[1-9].*");
    }

    public boolean isAmountValid () {
        return !amount.isEmpty() && TextUtils.isDigitsOnly(amount)
                && amount.matches("^[1-9].*");
    }

    @Override
    public String toString() {
        return "CalculationData{" +
                "amount='" + amount + '\'' +
                ", threads='" + threads + '\'' +
                ", checked=" + checked +
                '}';
    }

    public int getAmount() {
        return Integer.parseInt(amount);
    }

    public int getThreads() {
        return Integer.parseInt(threads);
    }
}

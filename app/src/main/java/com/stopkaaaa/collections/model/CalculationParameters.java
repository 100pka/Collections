package com.stopkaaaa.collections.model;

public class CalculationParameters {
    private final String amount;
    private final String threads;
    private final boolean checked;

    public CalculationParameters(String amount, String threads, boolean checked) {
        this.amount = amount;
        this.threads = threads;
        this.checked = checked;
    }

    public String getAmount() {
        return amount;
    }

    public String getThreads() {
        return threads;
    }

    public boolean isChecked() {
        return checked;
    }

    @Override
    public String toString() {
        return "CalculationData{" +
                "amount='" + amount + '\'' +
                ", threads='" + threads + '\'' +
                ", checked=" + checked +
                '}';
    }
}

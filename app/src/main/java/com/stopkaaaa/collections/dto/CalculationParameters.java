package com.stopkaaaa.collections.dto;

public class CalculationParameters {
    private final String amount;
    private final String threads;
    private final boolean checked;
    private int amountValidated;
    private int threadsValidated;

    public CalculationParameters(String amount, String threads, boolean checked) {
        this.amount = amount;
        this.threads = threads;
        this.checked = checked;
    }

    public int getAmount() {
        return amountValidated;
    }

    public int getThreads() {
        return threadsValidated;
    }

    public boolean isChecked() {
        return checked;
    }


    public static boolean isThreadsValid (CalculationParameters calculationParameters) {
        try {
            calculationParameters.threadsValidated = Integer.parseInt(calculationParameters.threads);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isAmountValid (CalculationParameters calculationParameters) {
        try {
            calculationParameters.amountValidated = Integer.parseInt(calculationParameters.amount);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
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

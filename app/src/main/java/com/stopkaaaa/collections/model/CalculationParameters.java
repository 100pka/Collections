package com.stopkaaaa.collections.model;

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

    public static boolean validateParameters(CalculationParameters calculationParameters){
        try {
            calculationParameters.amountValidated = Integer.parseInt(calculationParameters.amount);
            calculationParameters.threadsValidated = Integer.parseInt(calculationParameters.threads);
        } catch (NumberFormatException e){
            return false;
        }
        if (calculationParameters.checked) {
            return true;
        } else return false;
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

package com.stopkaaaa.collections.model;

import com.stopkaaaa.collections.dto.CalculationResultItem;

public interface Calculator {
    public CalculationResultItem calculate(CalculationResultItem item, int size) throws InterruptedException;
}

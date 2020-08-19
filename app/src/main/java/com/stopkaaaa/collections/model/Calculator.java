package com.stopkaaaa.collections.model;

import com.stopkaaaa.collections.dto.CalculationResultItem;

public interface Calculator {
    public void setCollectionSize(int size);
    public String calculate(CalculationResultItem item);

}

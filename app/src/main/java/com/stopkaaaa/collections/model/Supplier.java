package com.stopkaaaa.collections.model;

import com.stopkaaaa.collections.dto.CalculationResultItem;

import java.util.List;

public interface Supplier {
    public List<CalculationResultItem> getTaskList();
    public int getSpanCount();
}

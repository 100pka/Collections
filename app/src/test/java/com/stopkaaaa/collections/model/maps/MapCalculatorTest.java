package com.stopkaaaa.collections.model.maps;

import android.content.Context;
import android.util.Log;

import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.dto.CalculationResultItem;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class MapCalculatorTest {
    @Mock
    private Context mockApplicationContext;

    @Before
    public void setupTests() {
        MockitoAnnotations.initMocks(this);

        when(mockApplicationContext.getString(R.string.hashMap)).thenReturn("HashMap");
        when(mockApplicationContext.getString(R.string.treeMap)).thenReturn("TreeMap");

        when(mockApplicationContext.getString(R.string.addingTo)).thenReturn("Adding to");
        when(mockApplicationContext.getString(R.string.removeFrom)).thenReturn("Removing from");
        when(mockApplicationContext.getString(R.string.searchInMap)).thenReturn("Search in map");
    }
    public static int[] listSize = {1, 111, 111111, 1111111, 7777777};

    @Test
    public void calculate() {
        List<CalculationResultItem> list = new MapSupplier(mockApplicationContext).getTaskList();
        MapCalculator mapCalculator = new MapCalculator(mockApplicationContext);
        CalculationResultItem result;
        for (int value : listSize) {
            for (CalculationResultItem item : list) {
                result = mapCalculator.calculate(item, value);
                assertNotEquals(result.getTime(), "0");
                Log.i("test", "calculate: " + result.getTime());
            }
        }
    }
}
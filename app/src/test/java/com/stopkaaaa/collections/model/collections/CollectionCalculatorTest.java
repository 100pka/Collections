package com.stopkaaaa.collections.model.collections;

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

public class CollectionCalculatorTest {

    @Mock
    private Context mockApplicationContext;

    @Before
    public void setupTests() {
        MockitoAnnotations.initMocks(this);

        when(mockApplicationContext.getString(R.string.arrayList)).thenReturn("ArrayList");
        when(mockApplicationContext.getString(R.string.linkedList)).thenReturn("LinkedList");
        when(mockApplicationContext.getString(R.string.copyOnWriteArrayList)).thenReturn("CopyOnWriteArrayList");

        when(mockApplicationContext.getString(R.string.addingToStart)).thenReturn("Adding to start in ");
        when(mockApplicationContext.getString(R.string.addingToMiddle)).thenReturn("Adding to middle in ");
        when(mockApplicationContext.getString(R.string.addingToEnd)).thenReturn("Adding to end in ");

        when(mockApplicationContext.getString(R.string.searchIn)).thenReturn("Search in ");

        when(mockApplicationContext.getString(R.string.removeFromStart)).thenReturn("Remove from start in ");
        when(mockApplicationContext.getString(R.string.removeFromMiddle)).thenReturn("Remove from middle in ");
        when(mockApplicationContext.getString(R.string.removeFromEnd)).thenReturn("Remove from end in ");
    }

    public static int[] listSize = {1, 111, 111111, 1111111, 11111111};

    @Test
    public void testCalculateIsReturningCorrectValues() {
        List<CalculationResultItem> list = new CollectionSupplier(mockApplicationContext).getTaskList();
        CollectionCalculator collectionCalculator = new CollectionCalculator(mockApplicationContext);
        CalculationResultItem result;
        for (int value : listSize) {
            for (CalculationResultItem item : list) {
                result = collectionCalculator.calculate(item, value);
                assertNotEquals(result.getTime(), "0");
                Log.i("test", "calculate: " + result.getTime());
            }
        }
    }
}
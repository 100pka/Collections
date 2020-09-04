package com.stopkaaaa.collections.model.collections;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;

import com.stopkaaaa.collections.dto.CalculationResultItem;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class CollectionCalculatorTest{

    public Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

    public static int[] listSize = {1, 111, 111111, 1111111, 11111111};

    @Test
    public void calculate() {
        List<CalculationResultItem> list = new CollectionSupplier(context).getTaskList();
        CollectionCalculator collectionCalculator = new CollectionCalculator(context);
        CalculationResultItem result;
        for (int i = 0; i < listSize.length; i++) {
            for (CalculationResultItem item: list) {
                result = collectionCalculator.calculate(item, listSize[i]);
                assertThat(result.getTime(), is(not("0")));
                Log.i("test", "calculate: " + result.getTime());
            }
        }
    }
}
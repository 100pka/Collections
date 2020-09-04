package com.stopkaaaa.collections.model.maps;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;

import com.stopkaaaa.collections.dto.CalculationResultItem;
import com.stopkaaaa.collections.model.collections.CollectionCalculator;
import com.stopkaaaa.collections.model.collections.CollectionSupplier;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class MapCalculatorTest {

    public Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    public static int[] listSize = {1, 111, 111111, 1111111, 7777777};

    @Test
    public void calculate() {
        List<CalculationResultItem> list = new MapSupplier(context).getTaskList();
        MapCalculator mapCalculator = new MapCalculator(context);
        CalculationResultItem result;
        for (int i = 0; i < listSize.length; i++) {
            for (CalculationResultItem item: list) {
                result = mapCalculator.calculate(item, listSize[i]);
                assertThat(result.getTime(), is(not("0")));
                Log.i("test", "calculate: " + result.getTime());
            }
        }
    }
}
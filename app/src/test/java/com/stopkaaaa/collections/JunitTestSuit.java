package com.stopkaaaa.collections;

import com.stopkaaaa.collections.dto.CalculationParametersTest;
import com.stopkaaaa.collections.model.collections.CollectionCalculator;
import com.stopkaaaa.collections.model.collections.CollectionCalculatorTest;
import com.stopkaaaa.collections.model.maps.MapCalculatorTest;
import com.stopkaaaa.collections.ui.fragment.mapcollection.MapCollectionPresenterTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(Suite.class)

@Suite.SuiteClasses({
        CalculationParametersTest.class,
        CollectionCalculatorTest.class,
        MapCalculatorTest.class,
        MapCollectionPresenterTest.class
})
public class JunitTestSuit {
}
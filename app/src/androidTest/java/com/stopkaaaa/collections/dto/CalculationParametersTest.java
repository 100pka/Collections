package com.stopkaaaa.collections.dto;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculationParametersTest {

    public static String[] input_false = {"", "0", "00001", "sdfs", "123sdf"};
    public static String[] input_true = {"123", "10", "99999", "101000000", "1"};

    @Test
    public void isThreadsValid() {
        CalculationParameters calculationParameters;
        for (int i = 0; i < input_true.length; i++) {
            calculationParameters = new CalculationParameters(input_true[i], input_true[i], true);
            assertTrue(calculationParameters.isThreadsValid());
            assertTrue(calculationParameters.isAmountValid());
        }
        for (int i = 0; i < input_false.length; i++) {
            calculationParameters = new CalculationParameters(input_false[i], input_false[i], true);
            assertFalse(calculationParameters.isThreadsValid());
            assertFalse(calculationParameters.isAmountValid());
        }

    }

}
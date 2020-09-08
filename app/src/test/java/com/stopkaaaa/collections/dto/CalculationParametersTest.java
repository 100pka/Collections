package com.stopkaaaa.collections.dto;

import android.text.TextUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TextUtils.class)
public class CalculationParametersTest {

    public static String[] input_false = {"", "0", "00001", "sdfs", "123sdf"};
    public static String[] input_true = {"123", "10", "99999", "101000000", "1"};

    @Before
    public void setup(){
        PowerMockito.mockStatic(TextUtils.class);
        PowerMockito.when(TextUtils.isDigitsOnly(any(CharSequence.class))).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                CharSequence sequence = (CharSequence) invocation.getArguments()[0];
                final int len = sequence.length();
                for (int cp, i = 0; i < len; i += Character.charCount(cp)) {
                    cp = Character.codePointAt(sequence, i);
                    if (!Character.isDigit(cp)) {
                        return false;
                    }
                }
                return true;
            }
        });
    }

    @Test
    public void isThreadsValid() {
        CalculationParameters calculationParameters;
        for (int i = 0; i < input_true.length; i++) {
            calculationParameters = new CalculationParameters(input_true[i], input_true[i], true);
            assertTrue(calculationParameters.isThreadsValid());
        }
        for (int i = 0; i < input_false.length; i++) {
            calculationParameters = new CalculationParameters(input_false[i], input_false[i], true);
            assertFalse(calculationParameters.isThreadsValid());
        }

    }

}
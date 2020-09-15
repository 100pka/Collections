package com.stopkaaaa.collections.ui.fragment.mapcollection;

import com.stopkaaaa.collections.base.BaseContract;
import com.stopkaaaa.collections.dto.CalculationParameters;
import com.stopkaaaa.collections.model.Calculator;
import com.stopkaaaa.collections.model.Supplier;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MapCollectionPresenterTest {

    private MapCollectionPresenter spyPresenter;

    private CalculationParameters parameters;

    @Mock
    BaseContract.BaseView view;

    @Mock
    Supplier supplier;

    @Mock
    Calculator calculator;

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                (Callable<Scheduler> schedulerCallable) -> Schedulers.trampoline());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        MapCollectionPresenter presenter = new MapCollectionPresenter(view, supplier, calculator);
        spyPresenter = Mockito.spy(presenter);
        parameters = Mockito.spy(new CalculationParameters("0", "0", false));

    }

    private void verifyNoMore() {
        Mockito.verifyNoMoreInteractions(view);
        Mockito.verifyNoMoreInteractions(supplier);
        Mockito.verifyNoMoreInteractions(calculator);
    }

    @Test
    public void testSetupIsNotDoingExcessInvocations() {
        spyPresenter.setup();
        verify(view).setData(any());
        verify(supplier).getTaskList();
        verifyNoMore();
    }

    @Test
    public void testGetSpanCountReturnsCorrectValueOfColumns() {
        when(supplier.getSpanCount()).thenReturn(3);
        assertEquals(3, spyPresenter.getSpanCount());
        verify(supplier).getSpanCount();
        verifyNoMore();
    }

    // calculationParameters checked and valid
    @Test
    public void testOnCalculationLaunchWhenParametersIsValid() {
        when(parameters.isChecked()).thenReturn(true);
        when(parameters.isAmountValid()).thenReturn(true);
        when(parameters.isThreadsValid()).thenReturn(true);
        when(parameters.getThreads()).thenReturn(1);
        spyPresenter.onCalculationLaunch(parameters);
        verify(spyPresenter).startCalculation(parameters);
        verify(supplier).getTaskList();
        verify(view).uncheckStartButton();
        verify(view).showProgressBar(anyBoolean());
        verifyNoMore();
    }

    @Test // calculationParameters not Checked
    public void testOnCalculationLaunchWhenParametersIsNotChecked() {
        when(parameters.isChecked()).thenReturn(false);
        spyPresenter.onCalculationLaunch(parameters);
        verify(spyPresenter).stopCalculation();
        verify(view).showProgressBar(false);
        verifyNoMore();
    }

    @Test // calculationParameters checked and collectionSize not valid
    public void testOnCalculationLaunchWhenCollectionSizeIsNotValid() {
        when(parameters.isChecked()).thenReturn(true);
        when(parameters.isAmountValid()).thenReturn(false);
        when(parameters.isThreadsValid()).thenReturn(true);
        spyPresenter.onCalculationLaunch(parameters);
        verify(view).invalidCollectionSize();
        verify(view).uncheckStartButton();
        verifyNoMore();
    }

    @Test // calculationParameters checked and threadsAmount not valid
    public void testOnCalculationLaunchWhenThreadsAmountIsNotValid() {
        when(parameters.isChecked()).thenReturn(true);
        when(parameters.isAmountValid()).thenReturn(true);
        when(parameters.isThreadsValid()).thenReturn(false);
        spyPresenter.onCalculationLaunch(parameters);
        verify(view).invalidThreadsAmount();
        verify(view).uncheckStartButton();
        verifyNoMore();
    }

    @Test // calculationParameters = null
    public void testOnCalculationLaunchWhenParametersIsNull() {
        spyPresenter.onCalculationLaunch(null);
        verify(view).uncheckStartButton();
        verifyNoMore();
    }


    @Test
    public void testStopCalculationIsWorkingCorrectly() {
        spyPresenter.stopCalculation();
        verify(view).showProgressBar(false);
        verify(view).stopCalculationNotification();
        verifyNoMore();
    }
}
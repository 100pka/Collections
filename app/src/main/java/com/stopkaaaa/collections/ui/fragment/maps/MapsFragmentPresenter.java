package com.stopkaaaa.collections.ui.fragment.maps;

import com.stopkaaaa.collections.dto.CalculationParameters;
import com.stopkaaaa.collections.dto.CalculationResultItem;
import com.stopkaaaa.collections.model.maps.MapCalculator;
import com.stopkaaaa.collections.model.maps.MapSupplier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MapsFragmentPresenter implements MapsFragmentContract.Presenter{

    private final MapsFragmentContract.View mapsFragmentContractView;
    private MapSupplier mapSupplier;
    private MapCalculator calculator;
    private final BlockingQueue<Runnable> calculationQueue;
    private final ThreadPoolExecutor calculationThreadPool;

    public MapsFragmentPresenter(
            MapsFragmentContract.View mapsFragmentContractView,
            MapSupplier mapSupplier,
            MapCalculator calculator) {
        this.mapsFragmentContractView = mapsFragmentContractView;
        this.mapSupplier = mapSupplier;
        this.calculator = calculator;
        this.calculationQueue = new LinkedBlockingQueue<Runnable>();
        this.calculationThreadPool = new ThreadPoolExecutor(1, 1,
                50, TimeUnit.MILLISECONDS, calculationQueue);
    }

    @Override
    public void setup() {
        mapsFragmentContractView.setData(mapSupplier.getTaskList());
    }

    @Override
    public int getSpanCount() {
        return mapSupplier.getSpanCount();
    }

    @Override
    public void onCalculationLaunch(CalculationParameters calculationParameters) {
        if (calculationParameters != null && calculationParameters.isChecked()) {
            if (calculationParameters.isAmountValid()) {
                if (calculationParameters.isThreadsValid()) {
                    mapsFragmentContractView.showProgressBar(true);
                    startCalculation(calculationParameters);
                } else {
                    mapsFragmentContractView.invalidThreadsAmount();
                    mapsFragmentContractView.uncheckStartButton();
                }
            } else {
                if (calculationParameters.isThreadsValid()){
                    mapsFragmentContractView.invalidMapSize();
                    mapsFragmentContractView.uncheckStartButton();
                } else {
                    mapsFragmentContractView.invalidMapSize();
                    mapsFragmentContractView.invalidThreadsAmount();
                    mapsFragmentContractView.uncheckStartButton();
                }
            }
        }
    }

    public void startCalculation(final CalculationParameters calculationParameters) {
        calculationThreadPool.setCorePoolSize(calculationParameters.getThreads());
        calculationThreadPool.setMaximumPoolSize(calculationParameters.getThreads());

        calculator.setMapSize(calculationParameters.getAmount());
        final List<CalculationResultItem> tasks = mapSupplier.getTaskList();
        final List<CalculationResultItem> calculationResultItems = new ArrayList<>(tasks);

        for (final CalculationResultItem item: tasks) {
            calculationThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String resultTime = calculator.calculate(item);
                    mapsFragmentContractView.updateItem(tasks.indexOf(item), resultTime);
                    calculationResultItems.remove(item);
                    if (calculationResultItems.isEmpty()) {
                        mapsFragmentContractView.uncheckStartButton();
                    }
                }
            });
        }
    }
}

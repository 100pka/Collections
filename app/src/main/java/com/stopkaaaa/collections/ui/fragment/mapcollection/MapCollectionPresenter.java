package com.stopkaaaa.collections.ui.fragment.mapcollection;

import com.stopkaaaa.collections.base.BaseContract;
import com.stopkaaaa.collections.dto.CalculationParameters;
import com.stopkaaaa.collections.dto.CalculationResultItem;
import com.stopkaaaa.collections.model.Calculator;
import com.stopkaaaa.collections.model.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class MapCollectionPresenter<K extends Supplier, V extends Calculator> implements BaseContract.BasePresenter {

    private final BaseContract.BaseView collectionsFragmentContractView;
    private K collectionSupplier;
    private V calculator;
    private final BlockingQueue<Runnable> calculationQueue;
    private final ThreadPoolExecutor calculationThreadPool;

    public MapCollectionPresenter(
            BaseContract.BaseView collectionsFragmentContractView,
            K collectionSupplier,
            V calculator) {
        this.collectionsFragmentContractView = collectionsFragmentContractView;
        this.collectionSupplier = collectionSupplier;
        this.calculator = calculator;
        this.calculationQueue = new LinkedBlockingQueue<Runnable>();
        this.calculationThreadPool = new ThreadPoolExecutor(1, 1,
                50, TimeUnit.MILLISECONDS, calculationQueue);
    }

    @Override
    public void setup() {
        collectionsFragmentContractView.setData(collectionSupplier.getTaskList());
    }

    @Override
    public int getSpanCount() {
        return collectionSupplier.getSpanCount();
    }

    @Override
    public void onCalculationLaunch(CalculationParameters calculationParameters) {
        if (calculationParameters == null) {
            collectionsFragmentContractView.uncheckStartButton();
            return;
        }
        if (calculationParameters.isChecked()) {
            final boolean amountValid = calculationParameters.isAmountValid();
            if (!amountValid) {
                collectionsFragmentContractView.invalidCollectionSize();
            }
            final boolean threadsValid = calculationParameters.isThreadsValid();
            if (!threadsValid) {
                collectionsFragmentContractView.invalidThreadsAmount();
            }
            if (amountValid && threadsValid) {
                collectionsFragmentContractView.showProgressBar(true);
                startCalculation(calculationParameters);
            } else {
                collectionsFragmentContractView.uncheckStartButton();
            }
        } else {
            // stop calculation
            calculationQueue.clear();
            calculationThreadPool.shutdown();
            collectionsFragmentContractView.showProgressBar(false);
        }

    }

    public void startCalculation(final CalculationParameters calculationParameters) {
        calculationThreadPool.setCorePoolSize(calculationParameters.getThreads());
        calculationThreadPool.setMaximumPoolSize(calculationParameters.getThreads());
        calculator.setCollectionSize(calculationParameters.getAmount());
        final List<CalculationResultItem> tasks = collectionSupplier.getTaskList();
        final List<CalculationResultItem> calculationResultItems = new ArrayList<>(tasks);

        for (final CalculationResultItem item : tasks) {
            calculationThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String resultTime = calculator.calculate(item);
                    if (!calculationThreadPool.isShutdown()) {
                        collectionsFragmentContractView.updateItem(tasks.indexOf(item), resultTime);
                        calculationResultItems.remove(item);
                        if (calculationResultItems.isEmpty()) {
                            collectionsFragmentContractView.uncheckStartButton();
                        }
                    }
                }
            });
        }
    }
}

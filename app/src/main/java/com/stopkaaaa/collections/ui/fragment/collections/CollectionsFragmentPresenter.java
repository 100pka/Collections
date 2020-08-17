package com.stopkaaaa.collections.ui.fragment.collections;

import com.stopkaaaa.collections.dto.CalculationParameters;
import com.stopkaaaa.collections.dto.CalculationResultItem;
import com.stopkaaaa.collections.model.collections.CollectionCalculator;
import com.stopkaaaa.collections.model.collections.CollectionSupplier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class CollectionsFragmentPresenter implements CollectionsFragmentContract.Presenter {

    private final CollectionsFragmentContract.View collectionsFragmentContractView;
    private CollectionSupplier collectionSupplier;
    private CollectionCalculator calculator;
    private final BlockingQueue<Runnable> calculationQueue;
    private final ThreadPoolExecutor calculationThreadPool;

    public CollectionsFragmentPresenter(
            CollectionsFragmentContract.View collectionsFragmentContractView,
            CollectionSupplier collectionSupplier,
            CollectionCalculator calculator) {
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
        if (calculationParameters != null && calculationParameters.isChecked()) {
            if (calculationParameters.isAmountValid()) {
                if (calculationParameters.isThreadsValid()) {
                    collectionsFragmentContractView.showProgressBar(true);
                    startCalculation(calculationParameters);
                } else {
                    collectionsFragmentContractView.invalidThreadsAmount();
                    collectionsFragmentContractView.uncheckStartButton();
                }
            } else {
                if (calculationParameters.isThreadsValid()){
                    collectionsFragmentContractView.invalidCollectionSize();
                    collectionsFragmentContractView.uncheckStartButton();
                } else {
                    collectionsFragmentContractView.invalidCollectionSize();
                    collectionsFragmentContractView.invalidThreadsAmount();
                    collectionsFragmentContractView.uncheckStartButton();
                }
            }
        }
    }

    public void startCalculation(final CalculationParameters calculationParameters) {
        calculationThreadPool.setCorePoolSize(calculationParameters.getThreads());
        calculationThreadPool.setMaximumPoolSize(calculationParameters.getThreads());
        calculator.setListSize(calculationParameters.getAmount());
        final List<CalculationResultItem> tasks = collectionSupplier.getTaskList();
        final List<CalculationResultItem> calculationResultItems = new ArrayList<>(tasks);

        for (final CalculationResultItem item: tasks) {
            calculationThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String resultTime = calculator.calculate(item);
                    collectionsFragmentContractView.updateItem(tasks.indexOf(item), resultTime);
                    calculationResultItems.remove(item);
                    if (calculationResultItems.isEmpty()) {
                        collectionsFragmentContractView.uncheckStartButton();
                    }
                }
            });
        }
    }
}

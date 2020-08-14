package com.stopkaaaa.collections.ui.fragment.collections;

import com.stopkaaaa.collections.dto.CalculationParameters;
import com.stopkaaaa.collections.dto.CalculationResultItem;
import com.stopkaaaa.collections.model.collections.CollectionCalculator;
import com.stopkaaaa.collections.model.collections.CollectionSupplier;
import com.stopkaaaa.collections.model.ModelContract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class CollectionsFragmentPresenter implements CollectionsFragmentContract.Presenter, ModelContract.ModelPresenter {

    private final CollectionsFragmentContract.View collectionsFragmentContractView;

    private CollectionSupplier collectionSupplier;

    private final BlockingQueue<Runnable> calculationQueue;
    private final ThreadPoolExecutor calculationThreadPool;

    public CollectionsFragmentPresenter(CollectionsFragmentContract.View collectionsFragmentContractView, CollectionSupplier collectionSupplier) {
        this.collectionsFragmentContractView = collectionsFragmentContractView;
        this.collectionSupplier = collectionSupplier;
        this.calculationQueue = new LinkedBlockingQueue<Runnable>();
        this.calculationThreadPool = new ThreadPoolExecutor(1, 1,
                50, TimeUnit.MILLISECONDS, calculationQueue);
    }

    @Override
    public void setup() {
        collectionsFragmentContractView.setRecyclerAdapterData(collectionSupplier.getRecyclerData());
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

    @Override
    public void calculationFinished(String listType, String operation, String time) {
        List<CalculationResultItem> list = new ArrayList<>();
        list.addAll(collectionSupplier.getRecyclerData());
        for (CalculationResultItem item: list
             ) {
            if (item.getListType().equals(listType) && item.getOperation().equals(operation)) {
                collectionsFragmentContractView.updateItem(list.indexOf(item), time);
                break;
            }
        }
        if ((calculationThreadPool.getActiveCount() != 0 || !calculationThreadPool.getQueue().isEmpty())) {
            collectionsFragmentContractView.uncheckStartButton();
        }
    }

    public void startCalculation(final CalculationParameters calculationParameters) {
        calculationThreadPool.setCorePoolSize(calculationParameters.getThreads());
        calculationThreadPool.setMaximumPoolSize(calculationParameters.getThreads());

        final List<CollectionCalculator> tasks = Collections.synchronizedList(collectionSupplier
                .getTasks(calculationParameters.getAmount(), this));
        for (CollectionCalculator task: new ArrayList<>(tasks)) {
            calculationThreadPool.execute(task);
        }
    }
}

package com.stopkaaaa.collections.ui.fragment.collections;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.stopkaaaa.collections.dto.CalculationParameters;
import com.stopkaaaa.collections.dto.CalculationResultItem;
import com.stopkaaaa.collections.model.collections.CollectionSupplier;
import com.stopkaaaa.collections.model.ModelContract;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class CollectionsFragmentPresenter implements CollectionsFragmentContract.Presenter, ModelContract.ModelPresenter {

    private final CollectionsFragmentContract.View collectionsFragmentContractView;

    private LiveData<ArrayList<CalculationResultItem>> liveData;

    private CollectionSupplier collectionSupplier;

    public CollectionsFragmentPresenter(CollectionsFragmentContract.View collectionsFragmentContractView, CollectionSupplier collectionSupplier) {
        this.collectionsFragmentContractView = collectionsFragmentContractView;
        this.collectionSupplier = collectionSupplier;
    }

    @Override
    public void setup() {
        collectionsFragmentContractView.setRecyclerAdapterData(collectionSupplier.getListArrayList());
    }

    @Override
    public int getSpanCount() {
        return CollectionSupplier.getSpanCount();
    }

    @Override
    public void onCalculationLaunch(CalculationParameters calculationParameters) {

        if (calculationParameters != null && CalculationParameters.isAmountValid(calculationParameters) &&
        CalculationParameters.isThreadsValid(calculationParameters) && calculationParameters.isChecked()) {
            liveData = collectionSupplier.getData();
            liveData.observe((LifecycleOwner) collectionsFragmentContractView, new Observer<ArrayList<CalculationResultItem>>() {
                @Override
                public void onChanged(ArrayList<CalculationResultItem> calculationResultItems) {
                    collectionsFragmentContractView.setRecyclerAdapterData(collectionSupplier.getListArrayList());
                    if (!collectionSupplier.isCalculationFinished()) {
                        return;
                    }
                    calculationFinished();
                }
            });
            collectionSupplier.showProgress();
            collectionSupplier.setCalculationParameters(calculationParameters);
            startCalculation();
        } else if (!CalculationParameters.isAmountValid(calculationParameters)) {
            collectionsFragmentContractView.amountValidationError();
            collectionsFragmentContractView.uncheckStartButton();
        } else if (!CalculationParameters.isThreadsValid(calculationParameters)) {
            collectionsFragmentContractView.threadValidationError();
            collectionsFragmentContractView.uncheckStartButton();
        }

    }

    @Override
    public void calculationFinished() {
        collectionsFragmentContractView.uncheckStartButton();
    }

    private void startCalculation() {
        BlockingQueue<Runnable> calculationQueue = new LinkedBlockingQueue<Runnable>();
        ThreadPoolExecutor calculationThreadPool = new ThreadPoolExecutor(1, 1,
                50, TimeUnit.MILLISECONDS, calculationQueue);
        calculationThreadPool.execute(collectionSupplier);
    }
}

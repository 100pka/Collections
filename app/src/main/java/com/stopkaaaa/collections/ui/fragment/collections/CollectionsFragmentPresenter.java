package com.stopkaaaa.collections.ui.fragment.collections;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.stopkaaaa.collections.dto.CalculationParameters;
import com.stopkaaaa.collections.dto.CalculationResultItem;
import com.stopkaaaa.collections.model.CollectionSupplier;
import com.stopkaaaa.collections.model.ModelContract;

import java.util.ArrayList;


public class CollectionsFragmentPresenter implements CollectionsFragmentContract.Presenter, ModelContract.ModelPresenter {
    private static final int SPAN_COUNT = 3;

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
        return SPAN_COUNT;
    }

    @Override
    public void onStartButtonClicked(CalculationParameters calculationParameters) {
        if (calculationParameters != null && CalculationParameters.validateParameters(calculationParameters)) {
            startCalculation(calculationParameters);
        }
    }

    @Override
    public void calculationFinished() {
        collectionsFragmentContractView.uncheckStartButton();
    }

    private void startCalculation(CalculationParameters calculationParameters) {
        liveData = collectionSupplier.getData();
        liveData.observe((LifecycleOwner) collectionsFragmentContractView, new Observer<ArrayList<CalculationResultItem>>() {
            @Override
            public void onChanged(ArrayList<CalculationResultItem> calculationResultItems) {
                collectionsFragmentContractView.setRecyclerAdapterData(collectionSupplier.getListArrayList());
                for (CalculationResultItem item : collectionSupplier.getListArrayList()
                ) {
                    if (item.isState()) {
                        return;
                    }
                    collectionsFragmentContractView.uncheckStartButton();
                }
            }
        });
        collectionSupplier.setCalculationParameters(calculationParameters);
        collectionSupplier.calculation();
    }
}

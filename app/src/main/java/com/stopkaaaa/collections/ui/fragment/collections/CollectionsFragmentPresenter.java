package com.stopkaaaa.collections.ui.fragment.collections;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.stopkaaaa.collections.dto.CalculationParameters;
import com.stopkaaaa.collections.dto.CalculationResultItem;
import com.stopkaaaa.collections.model.CollectionSupplier;
import com.stopkaaaa.collections.model.ModelContract;

import java.util.ArrayList;


public class CollectionsFragmentPresenter implements CollectionsFragmentContract.Presenter, ModelContract.ModelPresenter {

    private final CollectionsFragmentContract.View collectionsFragmentContractView;

    private LiveData<ArrayList<CalculationResultItem>> liveData;

    private Context context;

    private CollectionSupplier collectionSupplier;

    public CollectionsFragmentPresenter(CollectionsFragmentContract.View collectionsFragmentContractView, Context context) {
        this.collectionsFragmentContractView = collectionsFragmentContractView;
        this.context = context;
        collectionSupplier = CollectionSupplier.getInstance(this, context);
    }

    public void setup() {
        collectionsFragmentContractView.setRecyclerAdapterData(collectionSupplier.getListArrayList());
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
        liveData.observe((LifecycleOwner) collectionsFragmentContractView.getContext(), new Observer<ArrayList<CalculationResultItem>>() {
            @Override
            public void onChanged(ArrayList<CalculationResultItem> calculationResultItems) {
                collectionsFragmentContractView.setRecyclerAdapterData(collectionSupplier.getListArrayList());
            }
        });
        collectionSupplier.setCalculationParameters(calculationParameters);
        collectionSupplier.calculation();
    }
}

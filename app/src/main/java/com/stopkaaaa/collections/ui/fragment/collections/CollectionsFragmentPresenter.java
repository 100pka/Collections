package com.stopkaaaa.collections.ui.fragment.collections;

import com.stopkaaaa.collections.model.CalculationParameters;

import com.stopkaaaa.collections.model.CollectionsResult;
import com.stopkaaaa.collections.model.CalculationResult;
import com.stopkaaaa.collections.model.ModelContract;
import java.util.ArrayList;


public class CollectionsFragmentPresenter implements CollectionsFragmentContract.Presenter, ModelContract.ModelPresenter {

    private final CollectionsFragmentContract.View mCollectionsFragmentContractView;

    private CollectionsResult collectionsResult;


    public CollectionsFragmentPresenter(CollectionsFragmentContract.View mCollectionsFragmentContractView) {
        this.mCollectionsFragmentContractView = mCollectionsFragmentContractView;
        collectionsResult = CollectionsResult.getInstance(this, mCollectionsFragmentContractView.getContext());
    }

    @Override
    public ArrayList<CalculationResult> getRecyclerData() {
        return collectionsResult.getListArrayList();
    }

    @Override
    public void onStartButtonClicked(CalculationParameters calculationParameters) {
        CalculationResult.setStartButtonClicked(true);
        notifyRecyclerAdapter();
        startCalculation(calculationParameters);


    }

    @Override
    public void notifyRecyclerAdapter() {
        mCollectionsFragmentContractView.notifyRecyclerAdapter();
    }

    private void startCalculation(CalculationParameters calculationParameters) {
        collectionsResult.setCalculationParameters(calculationParameters);
        collectionsResult.calculation();
    }
}

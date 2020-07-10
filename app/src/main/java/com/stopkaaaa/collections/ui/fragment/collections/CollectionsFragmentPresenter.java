package com.stopkaaaa.collections.ui.fragment.collections;

import com.stopkaaaa.collections.model.CalculationParameters;

import com.stopkaaaa.collections.model.CollectionsResult;
import com.stopkaaaa.collections.model.CalculationResult;
import com.stopkaaaa.collections.model.ModelContract;

import java.util.ArrayList;
import java.util.Collections;


public class CollectionsFragmentPresenter implements CollectionsFragmentContract.Presenter, ModelContract.ModelPresenter {

    private final CollectionsFragmentContract.View mCollectionsFragmentContractView;



    public CollectionsFragmentPresenter(CollectionsFragmentContract.View mCollectionsFragmentContractView) {
        this.mCollectionsFragmentContractView = mCollectionsFragmentContractView;
    }

    @Override
    public ArrayList<CalculationResult> getRecyclerData() {
        return CollectionsResult.getInstance(this, mCollectionsFragmentContractView.getContext())
                .getListArrayList();
    }

    @Override
    public void onStartButtonClicked(CalculationParameters calculationParameters) {
        if (calculationParameters != null && CalculationParameters.validateParameters(calculationParameters)) {
            startCalculation(calculationParameters);
        }
    }

    @Override
    public void updateRecyclerData() {
        mCollectionsFragmentContractView.notifyRecyclerAdapter();
    }

    @Override
    public void calculationFinished() {
        mCollectionsFragmentContractView.uncheckStartButton();
    }

    private void startCalculation(CalculationParameters calculationParameters) {
        CollectionsResult.getInstance(this, mCollectionsFragmentContractView.getContext())
                .setCalculationParameters(calculationParameters);
        CollectionsResult.getInstance(this, mCollectionsFragmentContractView.getContext())
                .calculation();
    }
}

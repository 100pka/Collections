package com.stopkaaaa.collections.ui.fragment.collections;

import com.stopkaaaa.collections.model.CalculationParameters;

import com.stopkaaaa.collections.model.CollectionsResult;
import com.stopkaaaa.collections.model.ListModel;
import com.stopkaaaa.collections.model.ModelContract;
import java.util.ArrayList;


public class CollectionsFragmentPresenter implements CollectionsFragmentContract.Presenter, ModelContract.ModelPresenter {

    private final CollectionsFragmentContract.View mCollectionsFragmentContractView;

    private CollectionsResult collectionsResult;


    public CollectionsFragmentPresenter(CollectionsFragmentContract.View mCollectionsFragmentContractView) {
        this.mCollectionsFragmentContractView = mCollectionsFragmentContractView;
        mCollectionsFragmentContractView.setPresenter(this);
        collectionsResult = CollectionsResult.getInstance(this);
    }

    @Override
    public ArrayList<ListModel> getRecyclerData() {
        return collectionsResult.getListArrayList();
    }

    @Override
    public void onStartButtonClicked(CalculationParameters calculationParameters) {
        ListModel.setStartButtonClicked(true);
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

package com.stopkaaaa.collections.ui.fragment.collections;

import com.stopkaaaa.collections.model.CalculationData;

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
    public void onStartButtonClicked(CalculationData calculationData) {
        ListModel.setStartButtonClicked(true);
        notifyRecyclerAdapter();
        startCalculation(calculationData);


    }

    @Override
    public void notifyRecyclerAdapter() {
        mCollectionsFragmentContractView.notifyRecyclerAdapter();
    }

    private void startCalculation(CalculationData calculationData) {
        collectionsResult.setCalculationData(calculationData);
        collectionsResult.calculation();
    }
}

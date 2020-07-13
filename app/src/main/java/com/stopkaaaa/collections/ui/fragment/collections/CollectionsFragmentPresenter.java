package com.stopkaaaa.collections.ui.fragment.collections;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.stopkaaaa.collections.model.CalculationParameters;

import com.stopkaaaa.collections.model.CollectionsResult;
import com.stopkaaaa.collections.model.CalculationResult;
import com.stopkaaaa.collections.model.ModelContract;

import java.util.ArrayList;


public class CollectionsFragmentPresenter implements CollectionsFragmentContract.Presenter, ModelContract.ModelPresenter {

    private final CollectionsFragmentContract.View mCollectionsFragmentContractView;

    private LiveData<ArrayList<CalculationResult>> liveData;

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
    public void calculationFinished() {
        mCollectionsFragmentContractView.uncheckStartButton();
    }

    private void startCalculation(CalculationParameters calculationParameters) {
        liveData = CollectionsResult.getInstance(this, mCollectionsFragmentContractView.getContext()).getData();
        liveData.observe((LifecycleOwner) mCollectionsFragmentContractView.getContext(), new Observer<ArrayList<CalculationResult>>() {
            @Override
            public void onChanged(ArrayList<CalculationResult> calculationResults) {
                mCollectionsFragmentContractView.notifyRecyclerAdapter();
            }
        });
        CollectionsResult.getInstance(this, mCollectionsFragmentContractView.getContext())
                .setCalculationParameters(calculationParameters);
        CollectionsResult.getInstance(this, mCollectionsFragmentContractView.getContext())
                .calculation();
    }
}

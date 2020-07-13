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

    private final CollectionsFragmentContract.View view;

    private LiveData<ArrayList<CalculationResult>> liveData;

    public CollectionsFragmentPresenter(CollectionsFragmentContract.View view) {
        this.view = view;
    }

    @Override
    public ArrayList<CalculationResult> getRecyclerData() {
        return CollectionsResult.getInstance(this, view.getContext())
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
        view.uncheckStartButton();
    }

    private void startCalculation(CalculationParameters calculationParameters) {
        liveData = CollectionsResult.getInstance(this, view.getContext()).getData();
        liveData.observe((LifecycleOwner) view.getContext(), new Observer<ArrayList<CalculationResult>>() {
            @Override
            public void onChanged(ArrayList<CalculationResult> calculationResults) {
                view.notifyRecyclerAdapter();
            }
        });
        CollectionsResult.getInstance(this, view.getContext())
                .setCalculationParameters(calculationParameters);
        CollectionsResult.getInstance(this, view.getContext())
                .calculation();
    }
}

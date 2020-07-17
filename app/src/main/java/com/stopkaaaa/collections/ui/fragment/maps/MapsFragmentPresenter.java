package com.stopkaaaa.collections.ui.fragment.maps;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.stopkaaaa.collections.dto.CalculationParameters;
import com.stopkaaaa.collections.dto.CalculationResultItem;
import com.stopkaaaa.collections.model.MapsSupplier;
import com.stopkaaaa.collections.model.ModelContract;

import java.util.ArrayList;

public class MapsFragmentPresenter implements MapsFragmentContract.Presenter, ModelContract.ModelPresenter {
    private static final int SPAN_COUNT = 2;

    private final MapsFragmentContract.View mapsFragmentContractView;

    private LiveData<ArrayList<CalculationResultItem>> liveData;

    private MapsSupplier mapsSupplier;

    public MapsFragmentPresenter(MapsFragmentContract.View mapsFragmentContractView, MapsSupplier mapsSupplier) {
        this.mapsFragmentContractView = mapsFragmentContractView;
        this.mapsSupplier = mapsSupplier;
    }

    @Override
    public void setup() {
        mapsFragmentContractView.setRecyclerAdapterData(mapsSupplier.getListArrayList());
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
        mapsFragmentContractView.uncheckStartButton();
    }

    private void startCalculation(CalculationParameters calculationParameters) {
        liveData = mapsSupplier.getData();
        liveData.observe((LifecycleOwner) mapsFragmentContractView, new Observer<ArrayList<CalculationResultItem>>() {
            @Override
            public void onChanged(ArrayList<CalculationResultItem> calculationResultItems) {
                mapsFragmentContractView.setRecyclerAdapterData(mapsSupplier.getListArrayList());
                for (CalculationResultItem item : mapsSupplier.getListArrayList()
                ) {
                    if (item.isState()) {
                        return;
                    }
                    mapsFragmentContractView.uncheckStartButton();
                }
            }
        });
        mapsSupplier.setCalculationParameters(calculationParameters);
        mapsSupplier.calculation();
    }
}

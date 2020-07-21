package com.stopkaaaa.collections.ui.fragment.maps;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.stopkaaaa.collections.dto.CalculationParameters;
import com.stopkaaaa.collections.dto.CalculationResultItem;
import com.stopkaaaa.collections.model.MapSupplier;
import com.stopkaaaa.collections.model.ModelContract;

import java.util.ArrayList;

public class MapsFragmentPresenter implements MapsFragmentContract.Presenter, ModelContract.ModelPresenter {

    private final MapsFragmentContract.View mapsFragmentContractView;

    private LiveData<ArrayList<CalculationResultItem>> liveData;

    private MapSupplier mapSupplier;

    public MapsFragmentPresenter(MapsFragmentContract.View mapsFragmentContractView, MapSupplier mapSupplier) {
        this.mapsFragmentContractView = mapsFragmentContractView;
        this.mapSupplier = mapSupplier;
    }

    @Override
    public void setup() {
        mapsFragmentContractView.setRecyclerAdapterData(mapSupplier.getListArrayList());
    }

    @Override
    public int getSpanCount() {
        return MapSupplier.getSpanCount();
    }

    @Override
    public void onCalculationLaunch(CalculationParameters calculationParameters) {
        if (calculationParameters != null && CalculationParameters.validateParameters(calculationParameters)) {
            startCalculation(calculationParameters);
        }
    }

    @Override
    public void calculationFinished() {
        mapsFragmentContractView.uncheckStartButton();
    }

    private void startCalculation(CalculationParameters calculationParameters) {
        liveData = mapSupplier.getData();
        liveData.observe((LifecycleOwner) mapsFragmentContractView, new Observer<ArrayList<CalculationResultItem>>() {
            @Override
            public void onChanged(ArrayList<CalculationResultItem> calculationResultItems) {
                mapsFragmentContractView.setRecyclerAdapterData(mapSupplier.getListArrayList());
                for (CalculationResultItem item : mapSupplier.getListArrayList()
                ) {
                    if (item.isState()) {
                        return;
                    }
                    mapsFragmentContractView.uncheckStartButton();
                }
            }
        });
        mapSupplier.setCalculationParameters(calculationParameters);
        mapSupplier.calculation();
    }
}

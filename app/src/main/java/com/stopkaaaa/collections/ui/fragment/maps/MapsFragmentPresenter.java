package com.stopkaaaa.collections.ui.fragment.maps;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.stopkaaaa.collections.dto.CalculationParameters;
import com.stopkaaaa.collections.dto.CalculationResultItem;
import com.stopkaaaa.collections.model.maps.MapSupplier;
import com.stopkaaaa.collections.model.ModelContract;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
        if (calculationParameters != null && CalculationParameters.isAmountValid(calculationParameters) &&
                CalculationParameters.isThreadsValid(calculationParameters) && calculationParameters.isChecked()) {
            liveData = mapSupplier.getData();
            liveData.observe((LifecycleOwner) mapsFragmentContractView, new Observer<ArrayList<CalculationResultItem>>() {
                @Override
                public void onChanged(ArrayList<CalculationResultItem> calculationResultItems) {
                    mapsFragmentContractView.setRecyclerAdapterData(mapSupplier.getListArrayList());
                    if (!mapSupplier.isCalculationFinished()) {
                        return;
                    }
                    calculationFinished();
                }
            });
            mapSupplier.showProgress();
            mapSupplier.setCalculationParameters(calculationParameters);
            startCalculation();
        } else if (!CalculationParameters.isAmountValid(calculationParameters)) {
            mapsFragmentContractView.amountValidationError();
        } else if (!CalculationParameters.isThreadsValid(calculationParameters)) {
            mapsFragmentContractView.threadValidationError();
        }
    }

    @Override
    public void calculationFinished() {
        mapsFragmentContractView.uncheckStartButton();
    }

    private void startCalculation() {
        BlockingQueue<Runnable> calculationQueue = new LinkedBlockingQueue<Runnable>();
        ThreadPoolExecutor calculationThreadPool = new ThreadPoolExecutor(1, 1,
                50, TimeUnit.MILLISECONDS, calculationQueue);
        calculationThreadPool.execute(mapSupplier);
    }
}

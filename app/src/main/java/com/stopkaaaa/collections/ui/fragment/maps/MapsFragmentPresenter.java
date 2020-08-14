package com.stopkaaaa.collections.ui.fragment.maps;

import com.stopkaaaa.collections.dto.CalculationParameters;
import com.stopkaaaa.collections.dto.CalculationResultItem;
import com.stopkaaaa.collections.model.maps.MapCalculator;
import com.stopkaaaa.collections.model.maps.MapSupplier;
import com.stopkaaaa.collections.model.ModelContract;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MapsFragmentPresenter implements MapsFragmentContract.Presenter, ModelContract.ModelPresenter {

    private final MapsFragmentContract.View mapsFragmentContractView;

    private MapSupplier mapSupplier;

    private final BlockingQueue<Runnable> calculationQueue;
    private final ThreadPoolExecutor calculationThreadPool;

    public MapsFragmentPresenter(MapsFragmentContract.View mapsFragmentContractView, MapSupplier mapSupplier) {
        this.mapsFragmentContractView = mapsFragmentContractView;
        this.mapSupplier = mapSupplier;
        this.calculationQueue = new LinkedBlockingQueue<Runnable>();
        this.calculationThreadPool = new ThreadPoolExecutor(1, 1,
                50, TimeUnit.MILLISECONDS, calculationQueue);
    }

    @Override
    public void setup() {
        mapsFragmentContractView.setRecyclerAdapterData(mapSupplier.getRecyclerData());
    }

    @Override
    public int getSpanCount() {
        return mapSupplier.getSpanCount();
    }

    @Override
    public void onCalculationLaunch(CalculationParameters calculationParameters) {
        if (calculationParameters != null && calculationParameters.isChecked()) {
            if (calculationParameters.isAmountValid()) {
                if (calculationParameters.isThreadsValid()) {
                    startCalculation(calculationParameters);
                } else {
                    mapsFragmentContractView.invalidThreadsAmount();
                    mapsFragmentContractView.uncheckStartButton();
                }
            } else {
                if (calculationParameters.isThreadsValid()){
                    mapsFragmentContractView.invalidMapSize();
                    mapsFragmentContractView.uncheckStartButton();
                } else {
                    mapsFragmentContractView.invalidMapSize();
                    mapsFragmentContractView.invalidThreadsAmount();
                    mapsFragmentContractView.uncheckStartButton();
                }
            }
        }
    }

    @Override
    public void calculationFinished(String listType, String operation, String time) {
        List<CalculationResultItem> list = new ArrayList<>();
        list.addAll(mapSupplier.getRecyclerData());
        for (CalculationResultItem item: list
        ) {
            if (item.getListType().equals(listType) && item.getOperation().equals(operation)) {
                mapsFragmentContractView.updateItem(list.indexOf(item), time);
                break;
            }
        }
    }

    public void startCalculation(final CalculationParameters calculationParameters) {
        calculationThreadPool.setCorePoolSize(calculationParameters.getThreads());
        calculationThreadPool.setMaximumPoolSize(calculationParameters.getThreads());

        new Thread(new Runnable() {
            ModelContract.ModelPresenter presenter;
            public Runnable init(ModelContract.ModelPresenter presenter) {
                this.presenter = presenter;
                return this;
            }
            @Override
            public void run() {
                for (final CalculationResultItem calculationResultItem : mapSupplier.getRecyclerData()
                ) {
                    MapCalculator calculator = new MapCalculator(
                            calculationParameters.getAmount(), calculationResultItem.getListType(),
                            calculationResultItem.getOperation(), mapSupplier.getContext(), presenter);
                    calculationThreadPool.execute(calculator);
                }
                while (calculationThreadPool.getActiveCount() != 0 || !calculationThreadPool.getQueue().isEmpty()){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                }
                mapsFragmentContractView.uncheckStartButton();
            }
        }.init(this)).start();
    }
}

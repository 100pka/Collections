package com.stopkaaaa.collections.ui.fragment.mapcollection;

import com.stopkaaaa.collections.base.BaseContract;
import com.stopkaaaa.collections.dto.CalculationParameters;
import com.stopkaaaa.collections.dto.CalculationResultItem;
import com.stopkaaaa.collections.model.Calculator;
import com.stopkaaaa.collections.model.Supplier;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MapCollectionPresenter implements BaseContract.BasePresenter {

    private final BaseContract.BaseView collectionsFragmentContractView;
    private Supplier collectionSupplier;
    private Calculator calculator;
    private final ThreadPoolExecutor calculationThreadPool;
    private Scheduler scheduler = Schedulers.single();
    private Disposable disposable = Disposable.disposed();

    public MapCollectionPresenter(
            BaseContract.BaseView collectionsFragmentContractView,
            Supplier collectionSupplier,
            Calculator calculator) {
        this.collectionsFragmentContractView = collectionsFragmentContractView;
        this.collectionSupplier = collectionSupplier;
        this.calculator = calculator;
        this.calculationThreadPool = new ThreadPoolExecutor(1, 1,
                50, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }

    @Override
    public void setup() {
        collectionsFragmentContractView.setData(collectionSupplier.getTaskList());
    }

    @Override
    public int getSpanCount() {
        return collectionSupplier.getSpanCount();
    }

    @Override
    public void onCalculationLaunch(CalculationParameters calculationParameters) {
        if (calculationParameters == null) {
            collectionsFragmentContractView.uncheckStartButton();
            return;
        }
        if (calculationParameters.isChecked()) {
            final boolean amountValid = calculationParameters.isAmountValid();
            if (!amountValid) {
                collectionsFragmentContractView.invalidCollectionSize();
            }
            final boolean threadsValid = calculationParameters.isThreadsValid();
            if (!threadsValid) {
                collectionsFragmentContractView.invalidThreadsAmount();
            }
            if (amountValid && threadsValid) {
                startCalculation(calculationParameters);
            } else {
                collectionsFragmentContractView.uncheckStartButton();
            }
        } else {
            // stop calculation
            stopCalculation();
        }

    }

    @Override
    public void stopCalculation() {
        disposable.dispose();
        scheduler.shutdown();
        collectionsFragmentContractView.showProgressBar(false);
    }

    public void startCalculation(final CalculationParameters calculationParameters) {
        calculationThreadPool.setCorePoolSize(calculationParameters.getThreads());
        calculationThreadPool.setMaximumPoolSize(calculationParameters.getThreads());
        scheduler = Schedulers.from(calculationThreadPool);

        final List<CalculationResultItem> tasks = collectionSupplier.getTaskList();
        final int size = calculationParameters.getAmount();

        disposable = (Disposable) Observable.fromIterable(tasks)
                .flatMap(task -> Observable.just(task)
                        .map(item -> calculator.calculate(item, size))
                        .subscribeOn(scheduler))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> collectionsFragmentContractView.showProgressBar(true))
                .doFinally(collectionsFragmentContractView::uncheckStartButton)
                .subscribe(result -> {
                    collectionsFragmentContractView.updateItem(tasks.indexOf(result), result.getTime());
                });
    }
}

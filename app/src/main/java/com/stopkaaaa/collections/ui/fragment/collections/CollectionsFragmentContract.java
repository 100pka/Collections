package com.stopkaaaa.collections.ui.fragment.collections;

import android.content.Context;

import com.stopkaaaa.collections.presenter.BasePresenter;
import com.stopkaaaa.collections.presenter.BaseView;
import com.stopkaaaa.collections.model.CalculationParameters;
import com.stopkaaaa.collections.model.CalculationResult;

import java.util.ArrayList;
import java.util.List;

public interface CollectionsFragmentContract {
    interface View extends BaseView<Presenter>{
        void notifyRecyclerAdapter();
        Context getContext();
    }
    interface Presenter extends BasePresenter{
        void onStartButtonClicked (CalculationParameters calculationParameters);
        ArrayList<CalculationResult> getRecyclerData();
    }
}

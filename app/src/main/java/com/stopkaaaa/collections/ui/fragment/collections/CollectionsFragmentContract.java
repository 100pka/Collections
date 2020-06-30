package com.stopkaaaa.collections.ui.fragment.collections;

import com.stopkaaaa.collections.BasePresenter;
import com.stopkaaaa.collections.BaseView;
import com.stopkaaaa.collections.model.CalculationParameters;
import com.stopkaaaa.collections.model.ListModel;

import java.util.ArrayList;

public interface CollectionsFragmentContract {
    interface View extends BaseView<Presenter>{
        void notifyRecyclerAdapter();
    }
    interface Presenter extends BasePresenter{
        void onStartButtonClicked (CalculationParameters calculationParameters);
        ArrayList<ListModel> getRecyclerData();
    }
}

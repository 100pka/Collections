package com.stopkaaaa.collections.ui.fragment.collections;

import com.stopkaaaa.collections.base.BaseContract;
import com.stopkaaaa.collections.dto.CalculationParameters;
import com.stopkaaaa.collections.dto.CalculationResultItem;

import java.util.List;

public interface CollectionsFragmentContract {
    interface View extends BaseContract.BaseView<Presenter> {
        void setRecyclerAdapterData(List<CalculationResultItem> list);

        void updateItem(int itemIndex, String time);

        void uncheckStartButton();

        void invalidCollectionSize();

        void invalidThreadsAmount();
    }

    interface Presenter extends BaseContract.BasePresenter {
        void onCalculationLaunch(CalculationParameters calculationParameters);

        void setup();

        int getSpanCount();
    }
}

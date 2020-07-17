package com.stopkaaaa.collections.ui.fragment.maps;

import com.stopkaaaa.collections.base.BaseContract;
import com.stopkaaaa.collections.dto.CalculationParameters;
import com.stopkaaaa.collections.dto.CalculationResultItem;

import java.util.ArrayList;

public interface MapsFragmentContract {
    interface View extends BaseContract.BaseView<Presenter> {
        void setRecyclerAdapterData(ArrayList<CalculationResultItem> list);

        void uncheckStartButton();
    }

    interface Presenter extends BaseContract.BasePresenter {
        void onStartButtonClicked(CalculationParameters calculationParameters);

        void setup();
        int getSpanCount();
    }
}

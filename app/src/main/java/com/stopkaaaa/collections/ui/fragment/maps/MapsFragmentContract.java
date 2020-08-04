package com.stopkaaaa.collections.ui.fragment.maps;

import com.stopkaaaa.collections.base.BaseContract;
import com.stopkaaaa.collections.dto.CalculationParameters;
import com.stopkaaaa.collections.dto.CalculationResultItem;

import java.util.List;

public interface MapsFragmentContract {
    interface View extends BaseContract.BaseView<Presenter> {
        void setRecyclerAdapterData(List<CalculationResultItem> list);

        void uncheckStartButton();

        void amountValidationError();

        void threadValidationError();
    }

    interface Presenter extends BaseContract.BasePresenter {
        void onCalculationLaunch(CalculationParameters calculationParameters);

        void setup();
        int getSpanCount();
    }
}

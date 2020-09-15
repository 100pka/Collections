package com.stopkaaaa.collections.base;

import com.stopkaaaa.collections.dto.CalculationParameters;
import com.stopkaaaa.collections.dto.CalculationResultItem;

import java.util.List;

public class BaseContract {
    public interface BasePresenter {
        void onCalculationLaunch(CalculationParameters calculationParameters);

        void setup();

        int getSpanCount();

        void stopCalculation();
    }

    public interface BaseView {
        void setData(List<CalculationResultItem> list);

        void updateItem(int itemIndex, String time);

        void uncheckStartButton();

        void invalidCollectionSize();

        void invalidThreadsAmount();

        void showProgressBar(boolean calculationInProgress);

        void stopCalculationNotification();
    }
}

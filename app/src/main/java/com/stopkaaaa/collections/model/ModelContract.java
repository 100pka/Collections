package com.stopkaaaa.collections.model;

import com.stopkaaaa.collections.base.BaseContract;
import com.stopkaaaa.collections.dto.CalculationParameters;

public interface ModelContract {
    interface Model {
        void startCalculation();
    }

    interface ModelPresenter extends BaseContract.BasePresenter {
        void calculationFinished();
    }
}

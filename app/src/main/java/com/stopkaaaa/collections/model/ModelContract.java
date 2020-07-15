package com.stopkaaaa.collections.model;

import com.stopkaaaa.collections.base.BaseContract;

public interface ModelContract {
    interface Model {
        void calculation();

    }
    interface ModelPresenter extends BaseContract.BasePresenter {
        void calculationFinished();
    }
}

package com.stopkaaaa.collections.model;

import com.stopkaaaa.collections.presenter.BasePresenter;

public interface ModelContract {
    interface Model {
        void calculation();

    }
    interface ModelPresenter extends BasePresenter {
        void calculationFinished();
    }
}

package com.stopkaaaa.collections.model;

import com.stopkaaaa.collections.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public interface ModelContract {
    interface Model {
        void calculation();

    }
    interface ModelPresenter extends BasePresenter {
        void notifyRecyclerAdapter();
    }
}

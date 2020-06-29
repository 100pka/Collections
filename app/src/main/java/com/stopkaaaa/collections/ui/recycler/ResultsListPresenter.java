package com.stopkaaaa.collections.ui.recycler;

import com.stopkaaaa.collections.model.ListModel;

import java.util.List;

public class ResultsListPresenter {

    private List<ListModel> calculationResults;

    public ResultsListPresenter(List<ListModel> calculationResults) {
        this.calculationResults = calculationResults;
    }

    public void onBindResultAtPosition(int position, RecyclerItemView itemView) {
        ListModel result = calculationResults.get(position);
        itemView.setItemName(result.getTitle());
        if (ListModel.isStartButtonClicked()) {
            itemView.setStartButtonClicked();
        }
        if (!result.getTime().equals("0 ms") && result.getTime() != null) {
            itemView.setItemTime(result.getTime());
        }
    }

    public int getCalculationResultCount() {
        return calculationResults.size();
    }

    public void setCalculationResults(List<ListModel> calculationResults) {
        this.calculationResults = calculationResults;
    }
}

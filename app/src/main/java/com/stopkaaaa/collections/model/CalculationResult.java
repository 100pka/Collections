package com.stopkaaaa.collections.model;

import com.google.common.util.concurrent.ListenableFutureTask;


public class CalculationResult {

    private String listType;
    private String operation;
    private String title;
    private String time;
    private boolean state;

    public CalculationResult(String listType, String operation) {
        this.listType = listType;
        this.operation = operation;
        this.state = false;
        this.time = "0";
        StringBuffer title = new StringBuffer().append(operation).append(" ").append(listType);
        this.title = title.toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}


package com.stopkaaaa.collections.model;

import com.google.common.util.concurrent.ListenableFutureTask;


public class CalculationResult {
    private static boolean startButtonClicked = false;

    private String listType;
    private String operation;
    private String title;
    private String time;
    private ListenableFutureTask<String> task;
    private State state;

    public CalculationResult(String listType, String operation, State state) {
        this.listType = listType;
        this.operation = operation;
        this.state = state;
        this.time = "0 ms";
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

    public static boolean isStartButtonClicked() {
        return startButtonClicked;
    }

    public static void setStartButtonClicked(boolean startButtonClicked) {
        CalculationResult.startButtonClicked = startButtonClicked;
    }

    public ListenableFutureTask<String> getTask() {
        return task;
    }

    public void setTask(ListenableFutureTask<String> task) {
        this.task = task;
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

enum State {
    CALCULATION,
    FINISHED
}
package com.stopkaaaa.collections.model;

import com.google.common.util.concurrent.ListenableFutureTask;


public class ListModel {
    private static boolean startButtonClicked = false;

    private String listType;
    private String operation;
    private String title;
    private String time;
    private ListenableFutureTask<String> task;

    public static final String[] listTypes = {"ArrayList", "LinkedList", "CopyOnWriteArrayList"};

    public static final String[] operations = {"Adding to start in ", "Adding to middle in ",
            "Adding to end in ", "Search in ", "Remove from start in ", "Remove from middle in ",
            "Remove from end in "};


    public ListModel(String listType, String operation) {
        this.listType = listType;
        this.operation = operation;
        this.time = "0 ms";
        StringBuffer title = new StringBuffer().append(operation).append(listType);
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
        ListModel.startButtonClicked = startButtonClicked;
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
}

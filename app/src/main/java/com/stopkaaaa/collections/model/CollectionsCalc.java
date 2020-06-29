package com.stopkaaaa.collections.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionsCalc implements Callable<String> {

    private List<Integer> list;
    private int amount;
    private String operation;
    private String listType;

    public CollectionsCalc(int amount, String listType, String operation) {
        this.amount = amount;
        this.operation = operation;
        this.listType = listType;
        listInit();
    }

    private void listInit(){
        switch (listType) {
            case ("LinkedList"):
                list = new LinkedList<>();
            case ("CopyOnWriteArrayList"):
                list = new CopyOnWriteArrayList<>();
            default:
                list = new ArrayList<>();
        }
        for (int i = 0; i < amount; i++) {
            list.add(i);
        }
    }

    @Override
    public String call() {
        long start, result;
        switch (operation) {
            case ("Adding to start in "):
                start = System.nanoTime();
                list.add(0, 99);
                result = System.nanoTime() - start;
                return String.valueOf(result/1000000.0) + " ms";
            case ("Adding to middle in "):
                start = System.nanoTime();
                list.add(list.size()/2, 99);
                result = System.nanoTime() - start;
                return String.valueOf(result/1000000.0) + " ms";
            case ("Adding to end in "):
                start = System.nanoTime();
                list.add(list.size()-1, 99);
                result = System.nanoTime() - start;
                return String.valueOf(result/1000000.0) + " ms";
            case ("Search in "):
                start = System.nanoTime();
                int rndIndex = new Random().nextInt(list.size());
                list.get(rndIndex);
                result = System.nanoTime() - start;
                return String.valueOf(result/1000000.0) + " ms";
            case ("Remove from start in "):
                start = System.nanoTime();
                list.remove(0);
                result = System.nanoTime() - start;
                return String.valueOf(result/1000000.0) + " ms";
            case ("Remove from middle in "):
                start = System.nanoTime();
                list.remove(list.size()/2);
                result = System.nanoTime() - start;
                return String.valueOf(result/1000000.0) + " ms";
            case ("Remove from end in "):
                start = System.nanoTime();
                list.remove(list.size()-1);
                result = System.nanoTime() - start;
                return String.valueOf(result/1000000.0) + " ms";

            default:
                return "0 ms";
        }
    }
}

package com.company;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.function.Predicate;

class ArrayProcessor implements Runnable {
    public enum SearchType {
        min,
        max
    }

    private int[] array;
    private SearchType searchType;
    private int minElem, maxElem;

    public ArrayProcessor(int[] array, SearchType searchType) {
        this.array = array;
        this.searchType = searchType;
        minElem = maxElem = array[0];
    }

    @Override
    public void run() {
        switch (searchType) {
            case min:
                minElem = Arrays.stream(array).min().getAsInt();
                //System.out.println("min = " + findElem);
                break;
            case max:
                maxElem = Arrays.stream(array).max().getAsInt();
                //System.out.println("max = " + findElem);
                break;
        }
    }

    public int getMinElem() {
        return minElem;
    }

    public int getMaxElem() {
        return maxElem;
    }
}

class ArrayProcessorManager implements Runnable {
    private int minElem, maxElem;

    private ArrayProcessor findMinProcessor, findMaxProcessor;
    private Thread findMinThread, findMaxThread;

    public ArrayProcessorManager(int[] array) {
        findMinProcessor = new ArrayProcessor(array, ArrayProcessor.SearchType.min);
        findMinThread = new Thread(findMinProcessor);

        findMaxProcessor = new ArrayProcessor(array, ArrayProcessor.SearchType.max);
        findMaxThread = new Thread(findMaxProcessor);

        minElem = maxElem = array[0];
    }

    @Override
    public void run() {
        findMinThread.start();
        findMaxThread.start();

        try {
            findMinThread.join();
            findMaxThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        minElem = findMinProcessor.getMinElem();
        maxElem = findMaxProcessor.getMaxElem();
    }

    public int getMinElem() {
        return minElem;
    }

    public int getMaxElem() {
        return maxElem;
    }
}

public class Main2 {
    public static void main(String[] args) throws Exception {
        int[] array = new int[]{3, 6, 1, 23, 45, 78};

        System.out.println("main start");

        ArrayProcessorManager manager = new ArrayProcessorManager(array);
        Thread thread = new Thread(manager);
        thread.start();
        thread.join();

        System.out.println("min = " + manager.getMinElem());
        System.out.println("max = " + manager.getMaxElem());

        System.out.println("main finish");
    }
}
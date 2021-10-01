package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Counter {
    public static int count = 0;
}

/*class CalculatorSiteLength implements Runnable{
    private String inUrl;
    public CalculatorSiteLength(String inUrl) {
        this.inUrl = inUrl;
    }
    @Override
    public void run() {
        try {
            URL url = new URL(inUrl);
            URLConnection connection = url.openConnection();
            InputStreamReader input = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(input);
            StringBuilder sb = new StringBuilder();
            while (reader.ready()) {
                sb.append(reader.readLine());
            }
            String htmlCode = sb.toString();
            Counter.count += htmlCode.length();
            System.out.println(inUrl + " done!");
        } catch (Exception e) {
        }
    }
}*/

public class Main4 {

    static void calculateSiteLength(String inUrl) {
        try {
            URL url = new URL(inUrl);
            URLConnection connection = url.openConnection();

            InputStreamReader input = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(input);

            StringBuilder sb = new StringBuilder();
            while (reader.ready()) {
                sb.append(reader.readLine());
            }

            String htmlCode = sb.toString();
            Counter.count += htmlCode.length();

            System.out.println(inUrl + " done!");

        } catch (Exception e) {
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayList<String> urls = new ArrayList<>();
        Collections.addAll(urls,
                "https://habr.com/ru/all/",
                "https://bash.im",
                "http://proglang.su/java/enumeration-interface",
                "https://hr-vector.com/java/class-scanner",
                "https://www.youtube.com/",
                "https://www.kinopoisk.ru",
                "https://www.ivi.ru",
                "https://www.ozon.ru/",
                "https://news.sportbox.ru",
                "https://www.ivi.ru/new/movie-new",
                "https://auto.ru/"
        );

//        ExecutorService es = Executors.newCachedThreadPool();

        long startTime = System.currentTimeMillis();

        /*for (String url : urls) {
            //(new Thread(new CalculatorSiteLength(url))).start();
            es.execute(new CalculatorSiteLength(url));
        }
        es.shutdown();
        es.awaitTermination(10, TimeUnit.SECONDS);*/

        for (String url : urls) {
            calculateSiteLength(url);
        }

        long finishTime = System.currentTimeMillis();

        long timespan = finishTime - startTime;

        System.out.println("Total length = " + Counter.count + " time span = " + timespan);
    }
}

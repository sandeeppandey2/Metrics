package com.demo.metrics.metricsdemo.service;

import com.demo.metrics.metricsdemo.model.Metric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.function.Predicate;

@Service
@Transactional
public class MetricService {

    private int numberOfSuccessRequest = 0 ;
    private int numberOfUnsuccessfulrequest = 0;
    private final HashMap<HashSet<String>,Long> averageTime = new HashMap<>();
    private final HashSet<String> api = new HashSet<>();


    public void calculateRequestMetrics() throws FileNotFoundException {
        Scanner scan = new Scanner(new File(("/Users/spandey/sandeep/metrics/metricsdemo/Metrics/src/main/resources/data.log")));
        while (scan.hasNext()) {
            String curLine = scan.nextLine();
            String[] splitted = curLine.split("\t");

            if ("REQUEST".equals(splitted[0]) && "OK".equals(splitted[6])) {

                numberOfSuccessRequest++;
                api.add(splitted[3]);
                System.out.println("hash set" + api);
               // averageTime.put(api,Long.parseLong(splitted[5]));

            }
            if ("REQUEST".equals(splitted[0]) && "KO".equals(splitted[6])){
                numberOfUnsuccessfulrequest++;

            }
        }
        averageTime.put(api,78L);
        scan.close();
    }


    void test(String line){
        if (line="san"){


        }


    }
    public Metric getMetrics() throws FileNotFoundException {
        numberOfSuccessRequest=0;
        numberOfUnsuccessfulrequest=0;
        calculateRequestMetrics();
        Metric metric = new Metric();
        metric.setNumberOfSuccessfulRequest(numberOfSuccessRequest);
        metric.setNumberOfUnsuccessfulRequest(numberOfUnsuccessfulrequest);
        metric.setTotalNumberOfRequest(numberOfSuccessRequest + numberOfUnsuccessfulrequest);
        metric.setAverageTime(averageTime);
        //metric.setPercentageRequestSuccess((numberOfSuccessRequest/(numberOfSuccessRequest + numberOfUnsuccessfulrequest))*100);
        return metric;
    }
}
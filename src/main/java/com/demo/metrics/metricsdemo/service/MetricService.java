package com.demo.metrics.metricsdemo.service;

import com.demo.metrics.metricsdemo.model.Metric;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Service
@Transactional
public class MetricService {

    private int numberOfSuccessRequest;
    private int numberOfUnsuccessfulrequest;
    private long totalEndTime;
    private long totalStartTime;

    public void calculateRequestMetrics() throws FileNotFoundException {

        String fileName = "logfile/data.log";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            String curLine = scan.nextLine();
            String[] splitted = curLine.split("\t");

            if ("REQUEST".equals(splitted[0]) && "OK".equals(splitted[6])) {

                numberOfSuccessRequest++;
                if ("api/test".equalsIgnoreCase(splitted[3])) {

                    calculateAverageTime(Long.parseLong(splitted[5]), Long.parseLong(splitted[4]));
                }

            }
            if ("REQUEST".equals(splitted[0]) && "KO".equals(splitted[6])) {
                numberOfUnsuccessfulrequest++;

            }
        }
        scan.close();
    }


    void calculateAverageTime(long endTime, long startTime) {
        totalEndTime = totalEndTime + endTime;
        totalStartTime = totalStartTime + startTime;
    }

    public Metric getMetrics() throws FileNotFoundException {

        numberOfSuccessRequest = 0;
        numberOfUnsuccessfulrequest = 0;
        totalEndTime = 0;
        totalStartTime = 0;
        calculateRequestMetrics();
        Metric metric = new Metric();
        metric.setNumberOfSuccessfulRequest(numberOfSuccessRequest);
        metric.setNumberOfUnsuccessfulRequest(numberOfUnsuccessfulrequest);
        metric.setTotalNumberOfRequest(numberOfSuccessRequest + numberOfUnsuccessfulrequest);
        metric.setAverageTime((totalEndTime - totalStartTime) / numberOfSuccessRequest);
        metric.setPercentageRequestSuccess((numberOfSuccessRequest / (numberOfSuccessRequest + numberOfUnsuccessfulrequest)) * 100);
        return metric;
    }
}
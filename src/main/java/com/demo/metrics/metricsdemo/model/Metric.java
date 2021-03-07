package com.demo.metrics.metricsdemo.model;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Data
public class Metric {

    private long numberOfSuccessfulRequest;
    private long numberOfUnsuccessfulRequest;
    private long totalNumberOfRequest;
    private long percentageRequestSuccess;
    private HashMap<HashSet<String>,Long> averageTime;
}

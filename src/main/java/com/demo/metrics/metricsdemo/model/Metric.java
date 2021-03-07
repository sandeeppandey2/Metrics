package com.demo.metrics.metricsdemo.model;

import lombok.Data;

@Data
public class Metric {

    private long numberOfSuccessfulRequest;
    private long numberOfUnsuccessfulRequest;
    private long totalNumberOfRequest;
    private long percentageRequestSuccess;
    private long averageTime;
}

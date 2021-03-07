package com.demo.metrics.metricsdemo.controller;

import com.demo.metrics.metricsdemo.model.Metric;
import com.demo.metrics.metricsdemo.service.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/metrics")
public class MetricsController {

    @Autowired
    public MetricService metricService;

    @GetMapping
    public  ResponseEntity<Metric> getMetricsData() throws FileNotFoundException {

       return ResponseEntity.ok().body(metricService.getMetrics());
    }

}

package com.demo.metrics.metricsdemo.controller;

import com.demo.metrics.metricsdemo.service.MetricService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value=MetricsController.class)
public class MetricsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MetricService metricService;

    @Test
    public void testGetMetrics() throws Exception {

        mvc.perform(get("/metrics")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()
                );
    }
}

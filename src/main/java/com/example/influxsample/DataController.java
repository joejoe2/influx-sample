package com.example.influxsample;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.domain.WritePrecision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.time.Instant;

@Controller
@RequestMapping(path = "/api/data")
public class DataController {
    @Autowired
    private InfluxDBClient influxDBClient;

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody @Valid CreateSampleRequest request){
        Sample sample = Sample.builder().tag(request.getTag()).value(request.getValue()).time(Instant.now()).build();
        influxDBClient.getWriteApiBlocking().writeMeasurement(WritePrecision.NS, sample);
        return ResponseEntity.ok().build();
    }
}

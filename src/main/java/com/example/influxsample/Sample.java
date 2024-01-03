package com.example.influxsample;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Measurement(name = "sample")
@Data
@Builder
public class Sample {
    @Column(tag = true)
    String tag;

    @Column
    Double value;

    @Column(timestamp = true)
    Instant time;
}

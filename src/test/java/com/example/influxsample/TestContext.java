package com.example.influxsample;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.FixedHostPortGenericContainer;
import org.testcontainers.containers.GenericContainer;

public class TestContext implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {
    static {
        GenericContainer influxdb = new FixedHostPortGenericContainer("influxdb:2.7.4")
                .withFixedExposedPort(8286, 8086);
        influxdb.withEnv("DOCKER_INFLUXDB_INIT_MODE", "setup")
                .withEnv("DOCKER_INFLUXDB_INIT_USERNAME", "admin")
                .withEnv("DOCKER_INFLUXDB_INIT_PASSWORD", "pa55ward")
                .withEnv("DOCKER_INFLUXDB_INIT_ORG", "org")
                .withEnv("DOCKER_INFLUXDB_INIT_BUCKET", "data")
                .withEnv("DOCKER_INFLUXDB_INIT_RETENTION", "1w")
                .withEnv("DOCKER_INFLUXDB_INIT_ADMIN_TOKEN", "pa55ward1ifopasdfojksapojfasojf");
        influxdb.start();
    }

    @Override
    public void beforeAll(ExtensionContext context) {
        // Your "before all tests" startup logic goes here
    }

    @Override
    public void close() {
        // Your "after all tests" logic goes here
    }
}

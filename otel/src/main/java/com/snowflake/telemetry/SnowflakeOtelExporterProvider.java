package com.snowflake.telemetry;

import com.intellij.openapi.diagnostic.Logger;
import io.opentelemetry.sdk.metrics.export.MetricExporter;
import io.opentelemetry.exporter.otlp.metrics.OtlpGrpcMetricExporter;
//import io.opentelemetry.exporter.otlp.http.metrics.OtlpHttpMetricExporter;
import com.intellij.platform.diagnostic.telemetry.impl.OpenTelemetryExporterProvider;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.Collections;
import io.opentelemetry.sdk.metrics.internal.data.DelegatingMetricExporter;

public class SnowflakeOtelExporterProvider implements OpenTelemetryExporterProvider {
    private static final Logger LOG = Logger.getInstance(SnowflakeOtelExporterProvider.class);

    @Override
    public List<MetricExporter> getMetricsExporters() {
        return Collections.singletonList(createMetricExporter());
    }

    private MetricExporter createMetricExporter() {
        String endpoint = "http://localhost:" + System.getProperty("idea.diagnostic.opentelemetry.otlp", "4317") + "/v1/metrics";
        LOG.warn("Creating OTLP HTTP metric exporter with endpoint: " + endpoint);
        LOG.warn("SF Creating OTLP HTTP metric exporter with endpoint: " + endpoint);
        LOG.warn("SF Creating OTLP HTTP metric exporter with endpoint: " + endpoint);
        LOG.warn("SF Creating OTLP HTTP metric exporter with endpoint: " + endpoint);

        
        return new DelegatingMetricExporter(OtlpGrpcMetricExporter.builder()
            .setEndpoint(endpoint)
            .setTimeout(5, TimeUnit.SECONDS)
            .build());
    }
}

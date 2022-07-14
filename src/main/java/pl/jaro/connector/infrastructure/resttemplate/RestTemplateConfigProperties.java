package pl.jaro.connector.infrastructure.resttemplate;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Configuration
@ConfigurationProperties(prefix = "app.rest")
@Data
public class RestTemplateConfigProperties {

    @DurationUnit(ChronoUnit.MILLIS)
    private Duration connectTimeoutInMillis;

    @DurationUnit(ChronoUnit.MILLIS)
    private Duration readTimeoutInMillis;
}
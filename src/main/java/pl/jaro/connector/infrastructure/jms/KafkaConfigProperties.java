package pl.jaro.connector.infrastructure.jms;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.kafka")
@Data
public class KafkaConfigProperties {

    private String bootstrapAddress;
}

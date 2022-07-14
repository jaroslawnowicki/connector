package pl.jaro.connector.infrastructure.jms;


import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import pl.jaro.connector.model.request.ResourceRequest;
import pl.jaro.connector.util.ResourceUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;

@Configuration
@EnableKafka
@AllArgsConstructor
public class KafkaConfig {

    private final KafkaConfigProperties kafkaConfigProperties;

    @Bean
    public ProducerFactory<String, ResourceRequest> producerFactory() {
        final Map<String, Object> props = new ConcurrentHashMap<>();
        props.put(BOOTSTRAP_SERVERS_CONFIG, kafkaConfigProperties.getBootstrapAddress());
        props.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        props.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public ConsumerFactory<String, ResourceRequest> consumerFactory() {
        final Map<String, Object> props = new ConcurrentHashMap<>();
        props.put(BOOTSTRAP_SERVERS_CONFIG, kafkaConfigProperties.getBootstrapAddress());
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                ResourceUtil.TOPIC_RESOURCE);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(ResourceRequest.class));
    }

    @Bean
    public KafkaTemplate<String, ResourceRequest> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ResourceRequest> kafkaListenerContainerFactory() {
        final ConcurrentKafkaListenerContainerFactory<String, ResourceRequest> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
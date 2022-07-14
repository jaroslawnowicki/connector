package pl.jaro.connector.infrastructure.jms;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pl.jaro.connector.domain.resource.MessageRepository;
import pl.jaro.connector.exception.ServerErrorException;
import pl.jaro.connector.model.request.ResourceRequest;
import pl.jaro.connector.util.ResourceUtil;

@Component
@Slf4j
@AllArgsConstructor
public class KafkaMessageDAO implements MessageRepository {

    private final KafkaTemplate<String, ResourceRequest> kafkaTemplate;

    @Override
    public void sendMessage(final ResourceRequest resourceRequest) {
        try {
            kafkaTemplate.send(ResourceUtil.TOPIC_RESOURCE, resourceRequest);
        } catch (Exception exception) {
            log.info("Error sent", exception);
            throw new ServerErrorException();
        }
    }
}

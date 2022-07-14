package pl.jaro.connector.domain.resource.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;
import pl.jaro.connector.domain.resource.ResourceDBRepository;
import pl.jaro.connector.domain.resource.ResourceRepository;
import pl.jaro.connector.model.request.ResourceRequest;

@Service
@AllArgsConstructor
@Slf4j
public class ResourceConsumerService {

    private final ResourceRepository resourceRepository;
    private final ResourceDBRepository resourceDBRepository;

    @RetryableTopic(
            attempts = "4",
            backoff = @Backoff(delay = 1000, multiplier = 2.0),
            autoCreateTopics = "false",
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE)
    @KafkaListener(topics = "resource", groupId = "resource")
    public void receivedUri(final ResourceRequest resourceRequest) {
        log.info("Received Message: " + resourceRequest);
        final String body = resourceRepository.getResource(resourceRequest);
        resourceDBRepository.save(resourceRequest.getResource(), body, resourceRequest.getTypeResource(), true);
    }

    @DltHandler
    public void dlt(final ResourceRequest resourceRequest) {
        log.info("DLT: " + resourceRequest);
        resourceDBRepository.save(resourceRequest.getResource(), null, resourceRequest.getTypeResource(), false);
    }
}

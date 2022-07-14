package pl.jaro.connector.domain.resource.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.jaro.connector.domain.resource.MessageRepository;
import pl.jaro.connector.domain.resource.ResourceService;
import pl.jaro.connector.model.request.ResourceRequest;
import pl.jaro.connector.model.response.Response;
import pl.jaro.connector.model.response.ResponseOk;


@Service
@AllArgsConstructor
@Slf4j
public class ResourceServiceImpl implements ResourceService {

    private final MessageRepository messageRepository;

    @Override
    public Response saveResource(final ResourceRequest resourceRequest) {
        log.info("Send message {}", resourceRequest);
        messageRepository.sendMessage(resourceRequest);
        return ResponseOk.builder()
                .message("Message is sent")
                .build();
    }
}

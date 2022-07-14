package pl.jaro.connector.domain.resource;

import pl.jaro.connector.model.request.ResourceRequest;

public interface MessageRepository {

    void sendMessage(final ResourceRequest resourceRequest);
}

package pl.jaro.connector.domain.resource;

import pl.jaro.connector.model.request.ResourceRequest;

public interface ResourceRepository {

    String getResource(final ResourceRequest resourceRequest);
}

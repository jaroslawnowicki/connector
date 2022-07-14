package pl.jaro.connector.domain.resource;

import pl.jaro.connector.model.request.ResourceRequest;
import pl.jaro.connector.model.response.Response;

public interface ResourceService {

    Response saveResource(final ResourceRequest resourceRequest);
}

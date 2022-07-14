package pl.jaro.connector.domain.resource;

import pl.jaro.connector.model.ResourceTypeEnum;

public interface ResourceDBRepository {

    void save(final String url, final String resourceBody, final ResourceTypeEnum resourceTypeEnum, final Boolean isSent);
}

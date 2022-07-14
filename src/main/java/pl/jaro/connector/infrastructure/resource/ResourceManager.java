package pl.jaro.connector.infrastructure.resource;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.jaro.connector.domain.resource.ResourceRepository;
import pl.jaro.connector.infrastructure.fileresource.FileResource;
import pl.jaro.connector.infrastructure.resttemplate.RestTemplateConnector;
import pl.jaro.connector.model.ResourceTypeEnum;
import pl.jaro.connector.model.request.ResourceRequest;

@Component
@AllArgsConstructor
public class ResourceManager implements ResourceRepository {

    final RestTemplateConnector restConnector;
    final FileResource fileResource;

    @Override
    public String getResource(ResourceRequest resourceRequest) {
        if (ResourceTypeEnum.SITE == resourceRequest.getTypeResource()) {
            return restConnector.getResource(resourceRequest.getResource());
        } else if (ResourceTypeEnum.FILE == resourceRequest.getTypeResource()){
            return fileResource.getResource(resourceRequest.getResource());
        } else {
            throw new UnsupportedOperationException();
        }
    }
}

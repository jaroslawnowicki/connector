package pl.jaro.connector.model.request;

import lombok.Data;
import pl.jaro.connector.model.ResourceTypeEnum;

@Data
public class ResourceRequest {

    private String resource;
    private ResourceTypeEnum typeResource;
}

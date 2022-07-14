package pl.jaro.connector.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jaro.connector.domain.resource.ResourceService;
import pl.jaro.connector.model.request.ResourceRequest;
import pl.jaro.connector.model.response.Response;


@RestController
@RequestMapping(value = "/api/v1/resource",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
@AllArgsConstructor
public class ResourceControllerRest {

    private final ResourceService resourceService;

    @PostMapping
    public Response saveResource(@RequestBody final ResourceRequest resourceRequest) {
        return resourceService.saveResource(resourceRequest);
    }
}

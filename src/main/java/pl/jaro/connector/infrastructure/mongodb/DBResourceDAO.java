package pl.jaro.connector.infrastructure.mongodb;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.jaro.connector.domain.resource.ResourceDBRepository;
import pl.jaro.connector.infrastructure.mongodb.entity.ResourceDocument;
import pl.jaro.connector.model.ResourceTypeEnum;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@Slf4j
public class DBResourceDAO implements ResourceDBRepository {

    private final MongoResourceRepository mongoResourceRepository;

    @Override
    public void save(final String url, final String resourceBody, final ResourceTypeEnum resourceTypeEnum, final Boolean isSent) {
        log.info("Resource save: Url: {}, isSent: {}", url, isSent);
        final ResourceDocument resourceDocument = ResourceDocument.builder()
                .url(url)
                .body(resourceBody)
                .typeResource(resourceTypeEnum.name())
                .insertDate(LocalDateTime.now())
                .isSent(isSent)
                .build();
        mongoResourceRepository.save(resourceDocument);
    }
}

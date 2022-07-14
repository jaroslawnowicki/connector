package pl.jaro.connector.infrastructure.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.jaro.connector.infrastructure.mongodb.entity.ResourceDocument;

@Repository
public interface MongoResourceRepository extends MongoRepository<ResourceDocument, String> {
}

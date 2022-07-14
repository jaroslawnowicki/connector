package pl.jaro.connector.infrastructure.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDocument {

    /**
     * Klucz głowny
     */
    @Id
    private String id;
    /**
     * Url do zasobu
     */
    private String url;
    /**
     * Zawartość pliku
     */
    private String body;
    /**
     * Typ zasobu
     */
    private String typeResource;
    /**
     * Data powstania obiektu
     */
    private LocalDateTime insertDate;
    /**
     * Czy zapytanie zostało wysłane pozytywnie
     */
    private Boolean isSent;

}

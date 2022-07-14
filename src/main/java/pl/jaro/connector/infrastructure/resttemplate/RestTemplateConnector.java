package pl.jaro.connector.infrastructure.resttemplate;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.jaro.connector.exception.ClientErrorException;
import pl.jaro.connector.exception.RedirectException;
import pl.jaro.connector.exception.ServerErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RestTemplateConnector {

    private final RestTemplate restTemplate;

    public String getResource(final String urlToResource) {
        try {
            final ResponseEntity<String> responseEntity = restTemplate.exchange(urlToResource, HttpMethod.GET, getHttpEntity(), String.class);
            return responseEntity.getBody();
        } catch (HttpClientErrorException httpClientErrorException) {
            log.error("Http Client Error", httpClientErrorException);
            checkStatusCode(httpClientErrorException.getStatusCode());
        }
        return "";
    }

    private HttpEntity<String> getHttpEntity() {
        final List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        return new HttpEntity<>(headers);
    }

    private void checkStatusCode(final HttpStatus status) throws ClientErrorException, RedirectException, ServerErrorException {
        if (status.is4xxClientError()) {
            throw new ClientErrorException();
        } else if (status.is3xxRedirection()) {
            throw new RedirectException();
        } else if (status.is5xxServerError()) {
            throw new ServerErrorException();
        }
    }
}

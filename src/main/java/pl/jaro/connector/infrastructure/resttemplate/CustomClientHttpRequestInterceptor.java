package pl.jaro.connector.infrastructure.resttemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@Slf4j
public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(final HttpRequest request,
                                        final byte[] body,
                                        final ClientHttpRequestExecution execution) throws IOException {
        logRequestDetails(request);
        return execution.execute(request, body);
    }

    private void logRequestDetails(final HttpRequest request) {
        log.info("Request Headers: {}", request.getHeaders());
        log.info("Request Method: {}", request.getMethod());
        log.info("Request URI: {}", request.getURI());
    }
}

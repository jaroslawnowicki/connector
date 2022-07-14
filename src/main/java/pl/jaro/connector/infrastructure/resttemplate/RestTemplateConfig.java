package pl.jaro.connector.infrastructure.resttemplate;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.client.RestTemplate;

@Configuration
@AllArgsConstructor
public class RestTemplateConfig {

    private final RestTemplateConfigProperties restConfigProperties;

    @Bean
    @Qualifier("customRestTemplateCustomizer")
    public CustomRestTemplateCustomizer customRestTemplateCustomizer() {
        return new CustomRestTemplateCustomizer();
    }

    @Bean
    @DependsOn(value = {"customRestTemplateCustomizer"})
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder(customRestTemplateCustomizer())
                .setConnectTimeout(restConfigProperties.getConnectTimeoutInMillis())
                .setReadTimeout(restConfigProperties.getReadTimeoutInMillis());
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}

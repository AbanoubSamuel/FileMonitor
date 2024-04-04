package org.abg.filemonitor.serviceImpl;

import org.abg.filemonitor.service.HttpService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class HttpServiceImpl implements HttpService {
    @Bean
    @Override
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList((request, body, execution) -> {
            HttpHeaders headers = request.getHeaders();
            headers.add("Accept", "application/json");
            headers.add("Content-Type", "application/json");
            return execution.execute(request, body);
        }));
        return restTemplate;
    }
}

package org.abg.filemonitor.service;

import org.springframework.web.client.RestTemplate;

public interface HttpService {
    RestTemplate restTemplate();
}

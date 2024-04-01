package org.abg.filemonitor.serviceImpl;

import org.abg.filemonitor.dto.LoginDto;
import org.abg.filemonitor.service.AuthService;
import org.abg.filemonitor.utls.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthImpl implements AuthService {
    @Autowired
    private  RestTemplate restTemplate;

    /**
     * @return JsonResponse
     */
    @Override
    public JsonResponse<Object> login(LoginDto loginDto) {
        JsonResponse response = restTemplate.postForObject("http://127.0.0.1:8000/api/v1/login", loginDto, JsonResponse.class);
        return response;
    }
}

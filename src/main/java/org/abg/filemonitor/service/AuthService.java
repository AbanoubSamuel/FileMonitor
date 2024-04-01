package org.abg.filemonitor.service;

import org.abg.filemonitor.dto.LoginDto;
import org.abg.filemonitor.utls.JsonResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    JsonResponse<Object> login(LoginDto loginDto);
}

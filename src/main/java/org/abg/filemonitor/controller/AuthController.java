package org.abg.filemonitor.controller;

import org.abg.filemonitor.dto.LoginDto;
import org.abg.filemonitor.serviceImpl.AuthImpl;
import org.abg.filemonitor.utls.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthImpl auth;

    /**
     * @param loginDto the LoginDto containing user credentials
     * @return ResponseEntity json response containing returned data
     */
    @PostMapping("/login")
    public ResponseEntity<JsonResponse<Object>> generateToken(@Validated @RequestBody LoginDto loginDto) {
        JsonResponse<Object> token = auth.login(loginDto);
        JsonResponse<Object> jsonResponse = JsonResponse
                .builder()
                .status("Success")
                .statusCode(200)
                .message("Logged in successfully")
                .data(token)
                .build();
        return new ResponseEntity<>(jsonResponse, HttpStatus.ACCEPTED);
    }
}

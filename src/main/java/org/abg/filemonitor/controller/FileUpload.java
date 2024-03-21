package org.abg.filemonitor.controller;

import jakarta.validation.Valid;
import org.abg.filemonitor.dto.FileDto;
import org.abg.filemonitor.utls.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/files")
public class FileUpload {
    @PostMapping("/upload")
    public ResponseEntity<JsonResponse<String>> fileUploader(@Valid @RequestBody FileDto fileDto) {
        JsonResponse<String> jsonResponse = new JsonResponse<>();
        jsonResponse.setStatus(true);
        jsonResponse.setMessage("File uploaded successfully");
        jsonResponse.setData(fileDto.getName());
        return new ResponseEntity<>(jsonResponse, HttpStatus.ACCEPTED);
    }
}

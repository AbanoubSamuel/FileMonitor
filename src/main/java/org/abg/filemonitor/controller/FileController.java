package org.abg.filemonitor.controller;

import jakarta.validation.Valid;
import org.abg.filemonitor.dto.FileDto;
import org.abg.filemonitor.entity.File;
import org.abg.filemonitor.mapper.FileMapper;
import org.abg.filemonitor.repository.FileRepository;
import org.abg.filemonitor.utls.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private FileRepository fileRepository;

    @PostMapping("/upload")
    public ResponseEntity<JsonResponse<FileDto>> fileUploader(@Valid @RequestBody FileDto fileDto) {
        File file = new File();
        file.setName("file");
        file.setLastUpdated("2024-03-30");
        fileRepository.saveAndFlush(file);
        FileDto fileDto1 = fileMapper.toDto(file);
        JsonResponse<FileDto> jsonResponse = new JsonResponse<>();
        jsonResponse.setStatus(true);
        jsonResponse.setMessage("File uploaded successfully");
        jsonResponse.setData(fileDto1);
        return new ResponseEntity<>(jsonResponse, HttpStatus.ACCEPTED);
    }
}

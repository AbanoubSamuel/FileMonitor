package org.abg.filemonitor.utls;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Path;

public class FileSender {
    private static final String TARGET_URL = "https://mocki.io/v1/b0b95932-7aee-473d-a097-c3bdac8f54bc"; // Replace with your target URL
    private static RestTemplate restTemplate;

    public FileSender(RestTemplate restTemplate) {
        FileSender.restTemplate = restTemplate;
    }

    public static JsonResponse sendFile(Path filePath) throws IOException {
        String fileName = filePath.getFileName().toString();

        // Check if the file has a .done extension
        if (!fileName.endsWith(".done")) {
            System.out.println("File does not have a .done extension: " + fileName);
            return null;
        }

        Path zipFilePath = FileZipper.zipFile(filePath);

        if (zipFilePath == null) {
            return null;
        }

        byte[] fileContent = "testFileContent".getBytes();

        MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
        ContentDisposition contentDisposition = ContentDisposition
                .builder("form-data")
                .name("file")
                .filename(fileName)
                .build();

        fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
        HttpEntity<byte[]> fileEntity = new HttpEntity<>(fileContent, fileMap);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileEntity);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        return restTemplate.getForEntity(TARGET_URL, JsonResponse.class)
                .getBody();
    }
}

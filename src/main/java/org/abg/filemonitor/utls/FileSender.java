package org.abg.filemonitor.utls;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.util.Objects;

public class FileSender {
    private static final String TARGET_URL = "http://example.com/upload"; // Replace with your target URL

    public static void sendFile(Path filePath) throws IOException {
        File zipFile = Objects.requireNonNull(FileZipper.zipFile(filePath))
                .toFile();

        try (
                FileInputStream fis = new FileInputStream(zipFile);
                OutputStream os = openConnectionAndGetOutputStream()
        ) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }

            os.flush();
        }
    }

    private static OutputStream openConnectionAndGetOutputStream() throws IOException {
        URL url = new URL(TARGET_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/zip");
        return connection.getOutputStream();
    }
}

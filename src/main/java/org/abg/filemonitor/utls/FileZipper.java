package org.abg.filemonitor.utls;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.abg.filemonitor.utls.Constant.DESTINATION_FOLDER;

public class FileZipper {
    public static Path zipFile(Path filePath) throws IOException {
        String fileName = filePath.getFileName().toString();

        // Check if the file has a .done extension

        // Remove the .done extension
        String zipFileName = fileName.substring(0, fileName.length() - 5);

        Path zipFilePath = Paths.get(DESTINATION_FOLDER, zipFileName + ".zip");

        if (Files.exists(zipFilePath)) {
            System.out.println("Zip file already exists for: " + fileName);
            return null;
        }

        Files.createDirectories(zipFilePath.getParent());

        try (
                FileOutputStream fos = new FileOutputStream(zipFilePath.toString());
                ZipOutputStream zipOut = new ZipOutputStream(fos);
                FileInputStream fis = new FileInputStream(filePath.toFile())
        ) {
            ZipEntry zipEntry = new ZipEntry(zipFileName);
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }

            zipOut.closeEntry();
            return zipFilePath;
        }
    }
}

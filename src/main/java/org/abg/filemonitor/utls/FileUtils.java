package org.abg.filemonitor.utls;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.abg.filemonitor.utls.Constant.DESTINATION_FOLDER;

public class FileUtils {
    public static void zipFile(Path filePath) {
        Path zipFilePath = Paths.get(DESTINATION_FOLDER, filePath.getFileName().toString() + ".zip");

        if (Files.exists(zipFilePath)) {
            System.out.println("Zip file already exists for: " + filePath.getFileName());
            return;
        }

        try {
            Files.createDirectories(zipFilePath.getParent());

            FileOutputStream fos = new FileOutputStream(zipFilePath.toString());
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            File fileToZip = new File(filePath.toString());

            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }

            zipOut.close();
            fis.close();
            fos.close();

            System.out.println("File zipped and saved to: " + zipFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

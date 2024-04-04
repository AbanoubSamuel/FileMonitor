package org.abg.filemonitor;

import org.abg.filemonitor.utls.FileSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.*;

import static org.abg.filemonitor.utls.Constant.SOURCE_FOLDER;
import static org.abg.filemonitor.utls.FileZipper.zipFile;

@SpringBootApplication
public class FileMonitor {
    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(FileMonitor.class, args);
        WatchService watchService = FileSystems.getDefault()
                .newWatchService();
        Path path = Paths.get(SOURCE_FOLDER);

        path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY
        );

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println(
                        "Event kind:" + event.kind()
                                + ". File affected: " + event.context() + ".");
                if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                    Path filePath = path.resolve((Path) event.context());
                    zipFile(filePath);
                    FileSender.sendFile(filePath);

                }
            }
            key.reset();
        }
    }
}


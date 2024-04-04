package org.abg.filemonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.*;

@SpringBootApplication
public class FileMonitor {
    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(FileMonitor.class, args);
        WatchService watchService = FileSystems.getDefault()
                .newWatchService();
        String directoryPathStr = "/home/ilinux/WorkSpace/ABG/FileMonitor/src/main/resources/uploads";

        Path path = Paths.get(directoryPathStr);

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
            }
            key.reset();
        }
    }
}


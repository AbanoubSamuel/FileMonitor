package org.abg.filemonitor.serviceImpl;

import org.abg.filemonitor.utls.FileSender;
import org.abg.filemonitor.utls.FileZipper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.*;

import static org.abg.filemonitor.utls.Constant.SOURCE_FOLDER;

@Service
public class FileWatcherImpl implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Path path = Paths.get(SOURCE_FOLDER);
        WatchService watchService = FileSystems.getDefault().newWatchService();

        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");
                Path fullPath = (Path) event.context();
                Path resolvedPath = path.resolve(fullPath);
                Path newPath = FileZipper.zipFile(resolvedPath);
                System.out.println("Event kind: " + event.kind() + ". File affected: " + newPath + ".");

//                FileSender.sendFile(filePath);
            }
            key.reset();
        }
    }
}

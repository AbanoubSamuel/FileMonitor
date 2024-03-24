package org.abg.filemonitor.serviceImpl;

import org.abg.filemonitor.service.FileWatcher;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchService;

public class FileWatcherImpl implements FileWatcher {
    public void fileScanner() throws IOException {
        WatchService watchService = FileSystems.getDefault()
                .newWatchService();
        Path path = Paths.get("/resources/uploads");
        System.out.println(path);
    }
}

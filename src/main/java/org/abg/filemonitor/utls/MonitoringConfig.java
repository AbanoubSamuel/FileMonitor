package org.abg.filemonitor.utls;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.*;

@Slf4j
@Configuration
//@EnableConfigurationProperties(FileWatcherProperties.class)
public class MonitoringConfig {
    // commiting class members, constructor and logger
    @Value("${application.file.watch.directory}")
    private String folderPath;

    @Bean
    public WatchService watchService() {
        log.debug("MONITORING_FOLDER: {}", folderPath);
        WatchService watchService = null;
        try {
            watchService = FileSystems.getDefault()
                    .newWatchService();
            Path path = Paths.get(folderPath);

            if (!Files.isDirectory(path)) {
                throw new RuntimeException("incorrect monitoring folder: " + path);
            }

            path.register(
                    watchService,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_CREATE
            );
        } catch (IOException e) {
            log.error("Exception for watch service creation:{} ", e.getMessage());
        }
        return watchService;
    }
}

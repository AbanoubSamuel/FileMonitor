package org.abg.filemonitor;

import org.abg.filemonitor.serviceImpl.FileWatcherImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileMonitor {
    public static void main(String[] args) {
        SpringApplication.run(FileMonitor.class, args);
    }
}


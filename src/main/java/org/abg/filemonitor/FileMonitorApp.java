package org.abg.filemonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileMonitorApp {

    public static void main(String[] args) {
        SpringApplication.run(FileMonitorApp.class, args);
        System.out.println("Spring DevTools Test...");
    }

}

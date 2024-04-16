package org.abg.filemonitor.utls;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@ConfigurationProperties(prefix = "application.file.watch")
@EnableConfigurationProperties(FileWatcherProperties.class)
public record FileWatcherProperties(
        @NotBlank String directory,
        boolean daemon,
        @Positive Long pollInterval,
        @Positive Long quietPeriod
) {
}

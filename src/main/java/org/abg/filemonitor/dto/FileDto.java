package org.abg.filemonitor.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link org.abg.filemonitor.entity.File}
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileDto implements Serializable {
    Long id;
    @Size(max = 250)
    String name;
    @Size(max = 100)
    String lastUpdated;
}
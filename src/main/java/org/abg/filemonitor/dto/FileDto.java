package org.abg.filemonitor.dto;

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
    String name;
    String lastUpdated;
}
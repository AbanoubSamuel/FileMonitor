package org.abg.filemonitor.dto;

import lombok.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * DTO for {@link org.abg.filemonitor.entity.File}
 */

@Getter
@Setter
@AllArgsConstructor
public class FileDto implements Serializable {
    Long id;
    String name;
    String last_updated;
}
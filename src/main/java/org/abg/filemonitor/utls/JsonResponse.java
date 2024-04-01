package org.abg.filemonitor.utls;

import jakarta.annotation.Nullable;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class JsonResponse<T> {
    private String status;
    private int statusCode;
    private String message;
    @Nullable
    private T data;
}

package org.abg.filemonitor.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto implements Serializable {
    @Size(max = 100, message = "Email cannot ")
    String email;
    @Size(max = 100, message = "Password cannot be blank")
    String password;
}

package org.suhacan.justdoit.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddUserRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}

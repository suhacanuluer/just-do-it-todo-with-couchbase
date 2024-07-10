package org.suhacan.justdoit.dto.request.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserRequest {
    private String password;
}

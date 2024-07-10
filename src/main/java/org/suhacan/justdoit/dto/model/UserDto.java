package org.suhacan.justdoit.dto.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String id;
    private String username;
    private String password;
}

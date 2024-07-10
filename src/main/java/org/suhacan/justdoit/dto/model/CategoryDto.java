package org.suhacan.justdoit.dto.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
    private String id;
    private String name;
    private String userId;
}

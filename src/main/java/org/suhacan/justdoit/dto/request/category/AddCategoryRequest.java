package org.suhacan.justdoit.dto.request.category;

import lombok.Data;

@Data
public class AddCategoryRequest {
    private String name;
    private String userId;
}

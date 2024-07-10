package org.suhacan.justdoit.dto.response.category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCategoryResponse {
    private String id;
    private String name;
}

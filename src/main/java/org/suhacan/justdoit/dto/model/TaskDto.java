package org.suhacan.justdoit.dto.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskDto {
    private String id;
    private String content;
    private String categoryId;
    private boolean completed;
    private boolean stared;
}

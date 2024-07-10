package org.suhacan.justdoit.dto.request.task;

import lombok.Data;

@Data
public class AddTaskRequest {
    private String content;
    private String categoryId;
}


package org.suhacan.justdoit.dto.request.task;

import lombok.Data;

@Data
public class UpdateTaskStatusRequest {
    private boolean isCompleted;
}

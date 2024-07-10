package org.suhacan.justdoit.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.suhacan.justdoit.dto.model.TaskDto;
import org.suhacan.justdoit.dto.request.task.*;
import org.suhacan.justdoit.entity.Task;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TaskMapper extends EntityMapper<TaskDto, Task>{
    TaskDto addTaskRequestToTaskDto(AddTaskRequest taskRequest);
    TaskDto updateTaskCategoryRequestToTaskDto(UpdateTaskCategoryRequest request);
    TaskDto updateTaskContentRequestToTaskDto(UpdateTaskContentRequest request);
}

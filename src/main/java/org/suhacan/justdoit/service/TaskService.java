package org.suhacan.justdoit.service;

import org.springframework.stereotype.Service;
import org.suhacan.justdoit.dto.model.TaskDto;

import java.util.List;

@Service
public interface TaskService {
    TaskDto addTask(TaskDto taskDto);
    String deleteTask(String taskId);
    TaskDto getTaskById(String taskId);
    List<TaskDto> getTaskListByCategoryId(String categoryId);
    TaskDto updateTaskStatus(String taskId, boolean isCompleted);
    TaskDto updateTaskStar(String taskId, boolean isStarred);
    TaskDto updateTaskContent(String taskId, TaskDto taskDto);
    TaskDto updateTaskCategory(String taskId, TaskDto taskDto);
    List<TaskDto> getAllTasksByUserId(String userId);
}

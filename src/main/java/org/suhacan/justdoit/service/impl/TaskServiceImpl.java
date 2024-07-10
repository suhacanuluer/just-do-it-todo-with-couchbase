package org.suhacan.justdoit.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.suhacan.justdoit.dto.model.CategoryDto;
import org.suhacan.justdoit.dto.model.TaskDto;
import org.suhacan.justdoit.entity.Task;
import org.suhacan.justdoit.exception.TaskNotFoundException;
import org.suhacan.justdoit.mapper.TaskMapper;
import org.suhacan.justdoit.repository.TaskRepository;
import org.suhacan.justdoit.service.CategoryService;
import org.suhacan.justdoit.service.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;
    private final CategoryService categoryService;

    @Override
    public TaskDto addTask(TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public String deleteTask(String taskId) {
        Task task = getTaskByIdOrThrow(taskId);
        taskRepository.delete(task);
        return task.getId();
    }

    @Override
    public TaskDto getTaskById(String taskId) {
        return taskMapper.toDto(getTaskByIdOrThrow(taskId));
    }

    @Override
    public List<TaskDto> getTaskListByCategoryId(String categoryId) {
        List<Task> taskList = taskRepository.findAllByCategoryId(categoryId);

        return taskMapper.toDto(taskList);
    }

    @Override
    public List<TaskDto> getAllTasksByUserId(String userId) {
        List<CategoryDto> categoryList = categoryService.getCategoryList(userId);

        return categoryList.stream()
                .flatMap(category -> getTaskListByCategoryId(category.getId()).stream())
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto updateTaskStatus(String taskId, boolean isCompleted) {
        Task task = getTaskByIdOrThrow(taskId);

        task.setCompleted(isCompleted);

        return updateTask(task);
    }

    @Override
    public TaskDto updateTaskStar(String taskId, boolean isStarred) {
        Task task = getTaskByIdOrThrow(taskId);

        task.setStared(isStarred);

        return updateTask(task);
    }

    @Override
    public TaskDto updateTaskContent(String taskId, TaskDto taskDto) {
        Task task = getTaskByIdOrThrow(taskId);

        task.setContent(taskDto.getContent());

        return updateTask(task);
    }

    @Override
    public TaskDto updateTaskCategory(String taskId, TaskDto taskDto) {
        Task task = getTaskByIdOrThrow(taskId);

        task.setCategoryId(taskDto.getCategoryId());

        return updateTask(task);
    }

    private TaskDto updateTask(Task task) {
        return taskMapper.toDto(taskRepository.save(task));
    }

    private Task getTaskByIdOrThrow(String taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }
}

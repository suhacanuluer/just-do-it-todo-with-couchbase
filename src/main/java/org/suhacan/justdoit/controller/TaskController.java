package org.suhacan.justdoit.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.suhacan.justdoit.dto.model.TaskDto;
import org.suhacan.justdoit.dto.request.task.*;
import org.suhacan.justdoit.mapper.TaskMapper;
import org.suhacan.justdoit.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @PostMapping("/add")
    public ResponseEntity<String> addTask(@RequestBody @Valid AddTaskRequest taskRequest){
        TaskDto taskDto = taskMapper.addTaskRequestToTaskDto(taskRequest);
        taskDto = taskService.addTask(taskDto);
        return ResponseEntity.ok(taskDto.getId());
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable("taskId") String taskId){
        TaskDto taskDto = taskService.getTaskById(taskId);
        return ResponseEntity.ok(taskDto);
    }

    @GetMapping("/category/{categoryId}/all")
    public ResponseEntity<List<TaskDto>> getTaskListByCategoryId(@PathVariable("categoryId") String categoryId){
        List<TaskDto> taskDto = taskService.getTaskListByCategoryId(categoryId);
        return ResponseEntity.ok(taskDto);
    }

    @GetMapping("/{userId}/all")
    public ResponseEntity<List<TaskDto>> getAllTasks(@PathVariable("userId") String userId){
        List<TaskDto> taskDto = taskService.getAllTasksByUserId(userId);
        return ResponseEntity.ok(taskDto);
    }

    //todo getCompletedTasks, getUncompletedTasks, getStarredTasks

    @PutMapping("/category/{taskId}")
    public ResponseEntity<TaskDto> updateTaskCategory(@PathVariable("taskId") String taskId, @RequestBody @Valid UpdateTaskCategoryRequest request){
        TaskDto taskDto = taskMapper.updateTaskCategoryRequestToTaskDto(request);
        taskDto = taskService.updateTaskCategory(taskId, taskDto);
        return ResponseEntity.ok(taskDto);
    }

    @PutMapping("/content/{taskId}")
    public ResponseEntity<TaskDto> updateTaskContent(@PathVariable("taskId") String taskId, @RequestBody @Valid UpdateTaskContentRequest request){
        TaskDto taskDto = taskMapper.updateTaskContentRequestToTaskDto(request);
        taskDto = taskService.updateTaskContent(taskId, taskDto);
        return ResponseEntity.ok(taskDto);
    }

    @PutMapping("/star/{taskId}")
    public ResponseEntity<TaskDto> updateTaskStar(@PathVariable("taskId") String taskId, @RequestBody @Valid UpdateTaskStarRequest request){
        TaskDto taskDto = taskService.updateTaskStar(taskId, request.isStarred());
        return ResponseEntity.ok(taskDto);
    }

    @PutMapping("/status/{taskId}")
    public ResponseEntity<TaskDto> updateTaskStatus(@PathVariable("taskId") String taskId, @RequestBody @Valid UpdateTaskStatusRequest request){
        TaskDto taskDto = taskService.updateTaskStatus(taskId, request.isCompleted());
        return ResponseEntity.ok(taskDto);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable("taskId") String taskId){
        String deletedTaskId = taskService.deleteTask(taskId);
        return ResponseEntity.ok(deletedTaskId);
    }
}

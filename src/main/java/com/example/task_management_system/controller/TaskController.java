package com.example.task_management_system.controller;

import com.example.task_management_system.dto.TaskDto;
import com.example.task_management_system.entity.Task;
import com.example.task_management_system.enums.Priority;
import com.example.task_management_system.enums.Status;
import com.example.task_management_system.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/{userId}/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task createTask(@PathVariable Long userId, @RequestBody TaskDto taskDto) {
        return taskService.createTask(taskDto);
    }

    @GetMapping("/{taskId}")
    public Task viewTask(@PathVariable Long userId, @PathVariable Long taskId) {
        return taskService.viewTask(taskId);
    }

    @PutMapping("/{taskId}")
    public Task editTask(@PathVariable Long userId, @PathVariable Long taskId, @RequestBody TaskDto taskDto) {
        return taskService.editTask(taskId, taskDto);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long userId, @PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }

    @PutMapping("/{taskId}/status")
    public Task changeTaskStatus(@PathVariable Long userId, @PathVariable Long taskId, @RequestParam Status newStatus) {
        return taskService.changeTaskStatus(taskId, newStatus);
    }

    @PutMapping("/{taskId}/priority")
    public Task changeTaskPriority(@PathVariable Long userId, @PathVariable Long taskId, @RequestParam Priority newPriority) {
        return taskService.changeTaskPriority(taskId, newPriority);
    }

    @PutMapping("/{taskId}/assign-executor")
    public Task assignExecutor(@PathVariable Long userId, @PathVariable Long taskId, @RequestParam Long executorId) {
        return taskService.assignExecutor(taskId, executorId);
    }

    @GetMapping
    public List<Task> getAllTasks(@PathVariable Long userId, @RequestParam int page, @RequestParam int size) {
        return taskService.getAllTasks(page, size);
    }

    @GetMapping("/filter")
    public List<Task> filterTasksByUserNames(@PathVariable Long userId,
                                             @RequestParam String authorName,
                                             @RequestParam String executorName,
                                             @RequestParam int page,
                                             @RequestParam int size) {
        return taskService.filterTasksByUserNames(authorName, executorName, page, size);
    }
}

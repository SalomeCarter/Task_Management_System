package com.example.task_management_system.service;

import com.example.task_management_system.dto.TaskDto;
import com.example.task_management_system.entity.Executor;
import com.example.task_management_system.entity.Task;
import com.example.task_management_system.entity.User;
import com.example.task_management_system.enums.Priority;
import com.example.task_management_system.enums.Status;
import com.example.task_management_system.repo.ExecutorRepository;
import com.example.task_management_system.repo.TaskRepository;
import com.example.task_management_system.repo.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class TaskService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExecutorRepository executorRepository;

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(TaskDto taskDto) {
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());

        task.setAuthor(findUserByIdOrThrow(taskDto.getUserId()));
        task.setExecutor(findExecutorByIdOrThrow(taskDto.getExecutorId()));

        return taskRepository.save(task);
    }

    private User findUserByIdOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
    }

    private Executor findExecutorByIdOrThrow(Long executorId) {
        return executorRepository.findById(executorId)
                .orElseThrow(() -> new EntityNotFoundException("Executor not found with id: " + executorId));
    }


    public Task editTask(Long taskId, TaskDto taskDto) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));

        existingTask.setName(taskDto.getName());
        existingTask.setDescription(taskDto.getDescription());

        User user = userRepository.findById(taskDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + taskDto.getUserId()));
        existingTask.setAuthor(user);

        Executor executor = executorRepository.findById(taskDto.getExecutorId())
                .orElseThrow(() -> new EntityNotFoundException("Executor not found with id: " + taskDto.getExecutorId()));
        existingTask.setExecutor(executor);

        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public Task viewTask(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));
    }

    public Task changeTaskStatus(Long taskId, Status newStatus) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));

        existingTask.setStatus(newStatus);
        return taskRepository.save(existingTask);
    }

    public Task changeTaskPriority(Long taskId, Priority newPriority) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));

        existingTask.setPriority(newPriority);
        return taskRepository.save(existingTask);
    }


    public Task assignExecutor(Long taskId, Long executorId) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));

        Executor executor = executorRepository.findById(executorId)
                .orElseThrow(() -> new EntityNotFoundException("Executor not found with id: " + executorId));

        existingTask.setExecutor(executor);
        return taskRepository.save(existingTask);
    }

    public List<Task> filterTasksByUserNames(String authorName, String executorName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Task> tasksPage = taskRepository.findByUser_NameOrExecutor_Name(authorName, executorName, pageable);

        return tasksPage.getContent();
    }

    public List<Task> getAllTasks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Task> tasksPage = taskRepository.findAll(pageable);

        return tasksPage.getContent();
    }

}

package com.example.task_management_system.controller;


import com.example.task_management_system.dto.TaskDto;
import com.example.task_management_system.entity.Task;
import com.example.task_management_system.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    public void testCreateTask() throws Exception {
        // Arrange
        TaskDto taskDto = new TaskDto();
        taskDto.setName("Test Task");

        // Mock сервиса
        when(taskService.createTask(any(TaskDto.class))).thenReturn(new Task());

        // Act and Assert
        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(taskDto)))
                .andExpect(status().isOk());
    }


}

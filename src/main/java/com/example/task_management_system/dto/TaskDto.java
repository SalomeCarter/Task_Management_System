package com.example.task_management_system.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
    private String name;
    private String description;
    private String status;
    private String priority;
    private Long userId;
    private Long executorId;

}

package com.example.task_management_system.repo;

import com.example.task_management_system.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findByUser_NameOrExecutor_Name(String authorName, String executorName, Pageable pageable);
       List<Task> findByUserId(long id);

        Optional<Task> findByName(String name);

        List<Task> findById(long id);
}

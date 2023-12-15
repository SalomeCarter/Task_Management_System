package com.example.task_management_system.repo;

import com.example.task_management_system.entity.Executor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutorRepository extends JpaRepository<Executor, Long> {
}

package com.example.task_management_system.service;

import com.example.task_management_system.entity.Executor;
import com.example.task_management_system.repo.ExecutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Transactional
@Service
public class ExecutorService {

    @Autowired
    private ExecutorRepository executorRepository;

    public List<Executor> getAllExecutors() {
        return executorRepository.findAll();
    }

    public Executor getExecutorById(Long executorId) {
        return executorRepository.findById(executorId)
                .orElseThrow(() -> new EntityNotFoundException("Executor not found with id: " + executorId));
    }

    public Executor createExecutor(Executor executor) {
        return executorRepository.save(executor);
    }

    public Executor updateExecutor(Long executorId, Executor updatedExecutor) {
        Executor existingExecutor = executorRepository.findById(executorId)
                .orElseThrow(() -> new EntityNotFoundException("Executor not found with id: " + executorId));

        existingExecutor.setId(updatedExecutor.getId());

        return executorRepository.save(existingExecutor);
    }

    public void deleteExecutor(Long executorId) {
        executorRepository.deleteById(executorId);
    }
}

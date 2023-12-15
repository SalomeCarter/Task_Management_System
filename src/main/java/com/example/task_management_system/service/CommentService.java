package com.example.task_management_system.service;

import com.example.task_management_system.entity.Comment;
import com.example.task_management_system.entity.Task;
import com.example.task_management_system.repo.CommentRepository;
import com.example.task_management_system.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TaskRepository taskRepository;


        public Comment createComment(Long taskId, Comment comment) {
            Task task = taskRepository.findById(taskId)
                    .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));

            comment.setTask(task);
            return commentRepository.save(comment);
        }

        public Comment updateComment(Long commentId, Comment updatedComment) {
            Comment existingComment = commentRepository.findById(commentId)
                    .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + commentId));

            existingComment.setText(updatedComment.getText());

            return commentRepository.save(existingComment);
        }

        public void deleteComment(Long commentId) {
            commentRepository.deleteById(commentId);
        }
    }



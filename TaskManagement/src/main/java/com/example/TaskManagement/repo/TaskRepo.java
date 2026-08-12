package com.example.TaskManagement.repo;

import com.example.TaskManagement.entity.Task;
import com.example.TaskManagement.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo  extends JpaRepository<Task, Long> {

    public List<Task> findTasksByStatus(TaskStatus status);
}

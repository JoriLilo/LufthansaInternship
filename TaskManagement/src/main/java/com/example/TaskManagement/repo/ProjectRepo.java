package com.example.TaskManagement.repo;

import com.example.TaskManagement.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project, Long> {


}

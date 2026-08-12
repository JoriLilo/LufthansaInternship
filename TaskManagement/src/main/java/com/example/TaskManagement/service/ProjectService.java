package com.example.TaskManagement.service;

import com.example.TaskManagement.dto.ProjectDTO;
import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.entity.ProjectStatus;
import com.example.TaskManagement.exception.ProjectNotFoundException;
import com.example.TaskManagement.repo.ProjectRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {


    private final ProjectRepo projectRepo;

    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }


    public void save(ProjectDTO projectDTO) {

        Project project = new Project();

        if (projectDTO.getName() != null) {
            project.setName(projectDTO.getName());
        }else {
            throw new IllegalArgumentException("Project name cannot be empty");
        }
        project.setDescription(projectDTO.getDescription());
        if (projectDTO.getStatus() != null) {
            project.setStatus(projectDTO.getStatus());
        }else {
            project.setStatus(ProjectStatus.ACTIVE);
        }

        projectRepo.save(project);

    }

    public List<ProjectDTO> getAll() {
        List<Project> projects = projectRepo.findAll();
        List<ProjectDTO> projectDTOs = new ArrayList<>();

        for (Project project : projects) {
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setName(project.getName());
            projectDTO.setDescription(project.getDescription());
            projectDTO.setStatus(project.getStatus());
            projectDTOs.add(projectDTO);

        }
        return projectDTOs;
    }

    public ProjectDTO getById(Long id) {
        Optional<Project> project = projectRepo.findById(id);
        if (project.isPresent()) {
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setName(project.get().getName());
            projectDTO.setDescription(project.get().getDescription());
            projectDTO.setStatus(project.get().getStatus());
            return projectDTO;
        }else{
            throw new ProjectNotFoundException("Project with id " + id + " not found");
        }
    }

    public void deleteById(Long id) {
        projectRepo.deleteById(id);
    }

}

package com.example.TaskManagement.service;

import com.example.TaskManagement.dto.TaskDTO;
import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.entity.ProjectStatus;
import com.example.TaskManagement.entity.Task;
import com.example.TaskManagement.entity.TaskStatus;
import com.example.TaskManagement.exception.ProjectCompletedException;
import com.example.TaskManagement.exception.ProjectNotFoundException;
import com.example.TaskManagement.exception.TaskNotFoundException;
import com.example.TaskManagement.repo.ProjectRepo;
import com.example.TaskManagement.repo.TaskRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final ProjectRepo projectRepo;
    private final TaskRepo taskRepo;

    public TaskService(ProjectRepo projectRepo, TaskRepo taskRepo) {
        this.projectRepo = projectRepo;
        this.taskRepo = taskRepo;
    }

    public void create(TaskDTO taskDTO) {
        Project project = projectRepo.findById(taskDTO.getProjectId())
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));
        if(project.getStatus() == ProjectStatus.COMPLETED){
            throw new ProjectCompletedException("Project already completed");
        }


        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setPriority(taskDTO.getPriority());
        task.setStatus(taskDTO.getStatus());
        task.setProject(project);

        taskRepo.save(task);

    }

    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepo.findAll();
        List<TaskDTO> taskDTOs = new ArrayList<>();
        for (Task task : tasks) {
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setTitle(task.getTitle());
            taskDTO.setDescription(task.getDescription());
            taskDTO.setPriority(task.getPriority());
            taskDTO.setStatus(task.getStatus());
            taskDTO.setProjectId(task.getProject().getId());
            taskDTOs.add(taskDTO);


        }
        return taskDTOs;
    }

    public TaskDTO getTaskById(Long id) {
            Task task= taskRepo.findById(id)
                    .orElseThrow(() -> new TaskNotFoundException("Task not found"));

            TaskDTO taskDTO = TaskDTO.builder()
                    .title(task.getTitle())
                    .description(task.getDescription())
                    .priority(task.getPriority())
                    .status(task.getStatus())
                    .projectId(task.getProject().getId())
                    .build();
            return taskDTO;

    }

    public void updateTaskStatus(Long id, TaskStatus newStatus) {

            Task task=taskRepo.findById(id)
                    .orElseThrow(() -> new TaskNotFoundException("Task not found"));
            task.setStatus(newStatus);
            taskRepo.save(task);

    }

    public void deleteTaskById(Long id) {
        if(taskRepo.findById(id).isPresent()){
            taskRepo.deleteById(id);
        }else
            throw new TaskNotFoundException("Task not found");

    }

    public List<TaskDTO> getTasksByProjectId(Long projectId) {
        Project project= projectRepo.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        List<Task> tasks = project.getTasks();
        List<TaskDTO> taskDTOs = new ArrayList<>();
        for (Task task : tasks) {

            TaskDTO taskDTO = TaskDTO.builder()
                    .title(task.getTitle())
                    .description(task.getDescription())
                    .priority(task.getPriority())
                    .status(task.getStatus())
                    .projectId(task.getProject().getId())
                    .build();
            taskDTOs.add(taskDTO);
        }
        return taskDTOs;
    }

    public List<TaskDTO> getTasksByStatus(TaskStatus status) {
        List<Task> tasks = taskRepo.findTasksByStatus(status);

        List<TaskDTO> taskDTOs = new ArrayList<>();
        for (Task task : tasks) {

                TaskDTO taskDTO = TaskDTO.builder()
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .priority(task.getPriority())
                        .status(task.getStatus())
                        .projectId(task.getProject().getId())
                        .build();
                taskDTOs.add(taskDTO);

        }

        return taskDTOs;

    }
}

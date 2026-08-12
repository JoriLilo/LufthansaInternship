package com.example.TaskManagement.controller;

import com.example.TaskManagement.dto.ProjectDTO;
import com.example.TaskManagement.entity.Project;
import com.example.TaskManagement.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/project")
public class ProjectController {

    private final  ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> save(@RequestParam ProjectDTO projectDTO) {
        projectService.save(projectDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProjectDTO>> getAll() {

        return new ResponseEntity<>(projectService.getAll(), HttpStatus.OK);
    }

}

package com.example.TaskManagement.dto;

import com.example.TaskManagement.entity.ProjectStatus;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProjectDTO {


    private String name;
    private String description;
    private ProjectStatus status;
}

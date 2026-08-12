package com.example.TaskManagement.dto;

import com.example.TaskManagement.entity.ProjectStatus;
import com.example.TaskManagement.entity.TaskPriority;
import com.example.TaskManagement.entity.TaskStatus;
import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private Long projectId;
}

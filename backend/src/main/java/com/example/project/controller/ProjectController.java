package com.example.project.controller;

import com.example.project.dto.ProjectCreateDto;
import com.example.project.dto.ProjectDto;
import com.example.project.dto.ProjectUpdateDto;
import com.example.project.entity.Project;
import com.example.project.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;
    
    @GetMapping
    public List<ProjectDto> getAllProjects() {
        return projectService.getAllProjects().stream()
                .map(Project::toDto)
                .toList();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id)
                .map(project -> ResponseEntity.ok(project.toDto()))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ProjectDto createProject(@Valid @RequestBody ProjectCreateDto projectDto) {
        return projectService.createProject(projectDto).toDto();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable Long id, @Valid @RequestBody ProjectUpdateDto projectDto) {
        try {
            Project updatedProject = projectService.updateProject(id, projectDto);
            return ResponseEntity.ok(updatedProject.toDto());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}

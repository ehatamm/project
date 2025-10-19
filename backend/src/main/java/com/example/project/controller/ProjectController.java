package com.example.project.controller;

import com.example.project.dto.ProjectCreateDto;
import com.example.project.dto.ProjectDto;
import com.example.project.dto.ProjectUpdateDto;
import com.example.project.entity.Project;
import com.example.project.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")
@Tag(name = "Projects", description = "Project management API endpoints")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;
    
    @Operation(summary = "Get all projects", description = "Retrieve a list of all projects")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved projects",
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ProjectDto.class)))
    })
    @GetMapping
    public List<ProjectDto> getAllProjects() {
        return projectService.getAllProjects().stream()
                .map(Project::toDto)
                .toList();
    }
    
    @Operation(summary = "Get project by ID", description = "Retrieve a specific project by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Project found",
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ProjectDto.class))),
        @ApiResponse(responseCode = "404", description = "Project not found",
            content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(
            @Parameter(description = "Project ID", required = true, example = "1")
            @PathVariable Long id) {
        return projectService.getProjectById(id)
                .map(project -> ResponseEntity.ok(project.toDto()))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "Create new project", description = "Create a new project with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Project created successfully",
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ProjectDto.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input data",
            content = @Content)
    })
    @PostMapping
    public ProjectDto createProject(
            @Parameter(description = "Project creation data", required = true)
            @Valid @RequestBody ProjectCreateDto projectDto) {
        return projectService.createProject(projectDto).toDto();
    }
    
    @Operation(summary = "Update project", description = "Update an existing project with new details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Project updated successfully",
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ProjectDto.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input data",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Project not found",
            content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> updateProject(
            @Parameter(description = "Project ID", required = true, example = "1")
            @PathVariable Long id, 
            @Parameter(description = "Project update data", required = true)
            @Valid @RequestBody ProjectUpdateDto projectDto) {
        try {
            Project updatedProject = projectService.updateProject(id, projectDto);
            return ResponseEntity.ok(updatedProject.toDto());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @Operation(summary = "Delete project", description = "Delete a project by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Project deleted successfully",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Project not found",
            content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(
            @Parameter(description = "Project ID", required = true, example = "1")
            @PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}

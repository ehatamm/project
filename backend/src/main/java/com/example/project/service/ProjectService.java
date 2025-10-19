package com.example.project.service;

import com.example.project.dto.ProjectCreateDto;
import com.example.project.dto.ProjectUpdateDto;
import com.example.project.entity.Project;
import com.example.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
    
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }
    
    public Project createProject(ProjectCreateDto projectDto) {
        Project project = Project.fromCreateDto(projectDto);
        return projectRepository.save(project);
    }
    
    public Project updateProject(Long id, ProjectUpdateDto projectDto) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
        
        Project updatedProject = Project.builder()
                .id(existingProject.getId())
                .name(projectDto.name())
                .description(projectDto.description())
                .startDate(projectDto.startDate())
                .endDate(projectDto.endDate())
                .createdAt(existingProject.getCreatedAt())
                .updatedAt(existingProject.getUpdatedAt())
                .build();
        
        return projectRepository.save(updatedProject);
    }
    
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}

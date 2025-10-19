package com.example.project.service;

import com.example.project.dto.ProjectCreateDto;
import com.example.project.dto.ProjectUpdateDto;
import com.example.project.entity.Project;
import com.example.project.exception.ProjectNotFoundException;
import com.example.project.mapper.ProjectMapper;
import com.example.project.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private ProjectMapper projectMapper;
    
    public List<Project> getAllProjects() {
        log.info("Retrieving all projects from database");
        List<Project> projects = projectRepository.findAll();
        log.info("Successfully retrieved {} projects from database", projects.size());
        return projects;
    }
    
    public Project getProjectById(Long id) {
        log.debug("Looking up project with ID: {}", id);
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            log.info("Found project with ID: {} and name: {}", id, project.get().getName());
            return project.get();
        } else {
            log.warn("Project not found with ID: {}", id);
            throw new ProjectNotFoundException("Project not found with id: " + id);
        }
    }
    
    public Project createProject(ProjectCreateDto projectDto) {
        log.info("Creating new project: {}", projectDto.name());
        Project project = projectMapper.projectCreateDtoToProject(projectDto);
        Project savedProject = projectRepository.save(project);
        log.info("Successfully created project with ID: {} and name: {}", 
                savedProject.getId(), savedProject.getName());
        return savedProject;
    }
    
    public Project updateProject(Long id, ProjectUpdateDto projectDto) {
        log.info("Updating project with ID: {} to name: {}", id, projectDto.name());
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Attempted to update non-existent project with ID: {}", id);
                    return new ProjectNotFoundException(id);
                });
        
        projectMapper.projectUpdateDtoToProject(projectDto, existingProject);
        Project savedProject = projectRepository.save(existingProject);
        log.info("Successfully updated project with ID: {}", id);
        return savedProject;
    }
    
    public void deleteProject(Long id) {
        log.info("Deleting project with ID: {}", id);
        if (!projectRepository.existsById(id)) {
            log.error("Attempted to delete non-existent project with ID: {}", id);
            throw new ProjectNotFoundException(id);
        }
        projectRepository.deleteById(id);
        log.info("Successfully deleted project with ID: {}", id);
    }
}

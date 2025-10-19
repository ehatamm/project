package com.example.project.service;

import com.example.project.dto.ProjectCreateDto;
import com.example.project.dto.ProjectUpdateDto;
import com.example.project.entity.Project;
import com.example.project.exception.ProjectNotFoundException;
import com.example.project.mapper.ProjectMapper;
import com.example.project.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMapper projectMapper;

    @InjectMocks
    private ProjectService projectService;

    @Test
    void shouldGetAllProjects() {
        // Given
        Project project1 = createTestProject(1L, "Project 1");
        Project project2 = createTestProject(2L, "Project 2");
        
        when(projectRepository.findAll()).thenReturn(List.of(project1, project2));

        // When
        List<Project> result = projectService.getAllProjects();

        // Then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Project 1");
        assertThat(result.get(1).getName()).isEqualTo("Project 2");
    }

    @Test
    void shouldGetProjectById() {
        // Given
        Project project = createTestProject(1L, "Test Project");

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        // When
        Project result = projectService.getProjectById(1L);

        // Then
        assertThat(result.getName()).isEqualTo("Test Project");
    }

    @Test
    void shouldThrowExceptionWhenProjectNotFound() {
        // Given
        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> projectService.getProjectById(1L))
                .isInstanceOf(ProjectNotFoundException.class)
                .hasMessage("Project not found with id: 1");
    }

    @Test
    void shouldCreateProject() {
        // Given
        ProjectCreateDto createDto = new ProjectCreateDto("New Project", "New Description", 
                LocalDate.now().plusDays(1), LocalDate.now().plusDays(30));
        Project project = createTestProject(1L, "New Project");
        
        when(projectMapper.projectCreateDtoToProject(createDto)).thenReturn(project);
        when(projectRepository.save(project)).thenReturn(project);

        // When
        Project result = projectService.createProject(createDto);

        // Then
        assertThat(result.getName()).isEqualTo("New Project");
        verify(projectRepository).save(project);
    }

    @Test
    void shouldUpdateProject() {
        // Given
        ProjectUpdateDto updateDto = new ProjectUpdateDto("Updated Project", "Updated Description", 
                LocalDate.now().plusDays(1), LocalDate.now().plusDays(30));
        Project existingProject = createTestProject(1L, "Old Project");
        
        when(projectRepository.findById(1L)).thenReturn(Optional.of(existingProject));
        when(projectRepository.save(existingProject)).thenReturn(existingProject);

        // When
        Project result = projectService.updateProject(1L, updateDto);

        // Then
        assertThat(result).isEqualTo(existingProject);
        verify(projectMapper).projectUpdateDtoToProject(updateDto, existingProject);
        verify(projectRepository).save(existingProject);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentProject() {
        // Given
        ProjectUpdateDto updateDto = new ProjectUpdateDto("Updated Project", "Updated Description", 
                LocalDate.now().plusDays(1), LocalDate.now().plusDays(30));
        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> projectService.updateProject(1L, updateDto))
                .isInstanceOf(ProjectNotFoundException.class)
                .hasMessage("Project not found with id: 1");
    }

    @Test
    void shouldDeleteProject() {
        // Given
        when(projectRepository.existsById(1L)).thenReturn(true);

        // When
        projectService.deleteProject(1L);

        // Then
        verify(projectRepository).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistentProject() {
        // Given
        when(projectRepository.existsById(1L)).thenReturn(false);

        // When & Then
        assertThatThrownBy(() -> projectService.deleteProject(1L))
                .isInstanceOf(ProjectNotFoundException.class)
                .hasMessage("Project not found with id: 1");
    }

    private Project createTestProject(Long id, String name) {
        return Project.builder()
                .id(id)
                .name(name)
                .description("Test Description")
                .startDate(LocalDate.now().plusDays(1))
                .endDate(LocalDate.now().plusDays(30))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}

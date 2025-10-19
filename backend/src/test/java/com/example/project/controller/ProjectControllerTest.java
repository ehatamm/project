package com.example.project.controller;

import com.example.project.dto.ProjectCreateDto;
import com.example.project.dto.ProjectDto;
import com.example.project.dto.ProjectUpdateDto;
import com.example.project.entity.Project;
import com.example.project.exception.ProjectNotFoundException;
import com.example.project.mapper.ProjectMapper;
import com.example.project.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProjectController.class)
@ActiveProfiles("test")
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProjectService projectService;

    @MockitoBean
    private ProjectMapper projectMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetAllProjects() throws Exception {
        // Given
        Project project1 = createTestProject(1L, "Project 1");
        Project project2 = createTestProject(2L, "Project 2");
        ProjectDto dto1 = new ProjectDto(1L, "Project 1", "Description 1", 
                LocalDate.now().plusDays(1), LocalDate.now().plusDays(30));
        ProjectDto dto2 = new ProjectDto(2L, "Project 2", "Description 2", 
                LocalDate.now().plusDays(1), LocalDate.now().plusDays(30));

        when(projectService.getAllProjects()).thenReturn(List.of(project1, project2));
        when(projectMapper.projectToProjectDto(project1)).thenReturn(dto1);
        when(projectMapper.projectToProjectDto(project2)).thenReturn(dto2);

        // When & Then
        mockMvc.perform(get("/api/projects"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("Project 1"))
                .andExpect(jsonPath("$[1].name").value("Project 2"));
    }

    @Test
    void shouldGetProjectById() throws Exception {
        // Given
        Project project = createTestProject(1L, "Test Project");
        ProjectDto dto = new ProjectDto(1L, "Test Project", "Test Description", 
                LocalDate.now().plusDays(1), LocalDate.now().plusDays(30));
        
        when(projectService.getProjectById(1L)).thenReturn(project);
        when(projectMapper.projectToProjectDto(project)).thenReturn(dto);

        // When & Then
        mockMvc.perform(get("/api/projects/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Project"));
    }

    @Test
    void shouldReturn404WhenProjectNotFound() throws Exception {
        // Given
        when(projectService.getProjectById(1L)).thenThrow(new ProjectNotFoundException("Project not found with id: 1"));

        // When & Then
        mockMvc.perform(get("/api/projects/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateProject() throws Exception {
        // Given
        ProjectCreateDto createDto = new ProjectCreateDto("New Project", "New Description", 
                LocalDate.now().plusDays(1), LocalDate.now().plusDays(30));
        Project createdProject = createTestProject(1L, "New Project");
        ProjectDto dto = new ProjectDto(1L, "New Project", "New Description", 
                LocalDate.now().plusDays(1), LocalDate.now().plusDays(30));
        
        when(projectService.createProject(any(ProjectCreateDto.class))).thenReturn(createdProject);
        when(projectMapper.projectToProjectDto(createdProject)).thenReturn(dto);

        // When & Then
        mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("New Project"));
    }

    @Test
    void shouldUpdateProject() throws Exception {
        // Given
        ProjectUpdateDto updateDto = new ProjectUpdateDto("Updated Project", "Updated Description", 
                LocalDate.now().plusDays(1), LocalDate.now().plusDays(30));
        Project updatedProject = createTestProject(1L, "Updated Project");
        ProjectDto dto = new ProjectDto(1L, "Updated Project", "Updated Description", 
                LocalDate.now().plusDays(1), LocalDate.now().plusDays(30));
        
        when(projectService.updateProject(anyLong(), any(ProjectUpdateDto.class))).thenReturn(updatedProject);
        when(projectMapper.projectToProjectDto(updatedProject)).thenReturn(dto);

        // When & Then
        mockMvc.perform(put("/api/projects/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Project"));
    }

    @Test
    void shouldDeleteProject() throws Exception {
        // When & Then
        mockMvc.perform(delete("/api/projects/1"))
                .andExpect(status().isNoContent());
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

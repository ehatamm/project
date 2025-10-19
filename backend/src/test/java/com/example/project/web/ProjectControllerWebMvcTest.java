package com.example.project.web;

import com.example.project.controller.ProjectController;
import com.example.project.dto.ProjectDto;
import com.example.project.entity.Project;
import com.example.project.mapper.ProjectMapper;
import com.example.project.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProjectController.class)
@ActiveProfiles("test")
class ProjectControllerWebMvcTest {
    
    @Autowired 
    MockMvc mvc;
    
    @MockitoBean 
    ProjectService projectService;
    
    @MockitoBean
    ProjectMapper projectMapper;
    
    @Test 
    void getProject_returns200() throws Exception {
        Project project = Project.builder()
                .id(42L)
                .name("Test Project")
                .description("Description")
                .startDate(LocalDate.now().plusDays(1))
                .endDate(LocalDate.now().plusDays(30))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
                
        ProjectDto dto = new ProjectDto(42L, "Test Project", "Description", 
                LocalDate.now().plusDays(1), LocalDate.now().plusDays(30));
        
        given(projectService.getProjectById(42L)).willReturn(project);
        given(projectMapper.projectToProjectDto(project)).willReturn(dto);
        
        mvc.perform(get("/api/projects/42"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Project"));
    }
}

package com.example.project.repository;

import com.example.project.entity.Project;
import com.example.project.testinfra.ItBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("it")
class ProjectRepositoryItTest extends ItBase {
    
    @Autowired 
    private ProjectRepository projectRepository;
    
    @Test 
    void savesAndFindsProject() {
        var project = Project.builder()
                .name("Alice Project")
                .description("Test Description")
                .startDate(LocalDate.now().plusDays(1))
                .endDate(LocalDate.now().plusDays(30))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        
        var saved = projectRepository.save(project);
        assertThat(projectRepository.findById(saved.getId())).isPresent();
    }

    @Test
    void shouldSaveProject() {
        Project project = Project.builder()
                .name("Test Project")
                .description("Test Description")
                .startDate(LocalDate.now().plusDays(1))
                .endDate(LocalDate.now().plusDays(30))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Project saved = projectRepository.save(project);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Test Project");
        assertThat(saved.getDescription()).isEqualTo("Test Description");
        assertThat(saved.getCreatedAt()).isNotNull();
        assertThat(saved.getUpdatedAt()).isNotNull();
    }

    @Test
    void shouldFindAllProjects() {
        Project project1 = createTestProject("Project 1");
        Project project2 = createTestProject("Project 2");
        projectRepository.save(project1);
        projectRepository.save(project2);

        List<Project> projects = projectRepository.findAll();

        assertThat(projects).hasSize(2);
        assertThat(projects).extracting(Project::getName)
                .containsExactlyInAnyOrder("Project 1", "Project 2");
    }

    @Test
    void shouldFindProjectById() {
        Project project = createTestProject("Test Project");
        Project saved = projectRepository.save(project);

        Optional<Project> found = projectRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Test Project");
    }

    @Test
    void shouldDeleteProject() {
        Project project = createTestProject("Test Project");
        Project saved = projectRepository.save(project);

        projectRepository.deleteById(saved.getId());

        Optional<Project> found = projectRepository.findById(saved.getId());
        assertThat(found).isEmpty();
    }

    @Test
    void shouldCheckProjectExists() {
        Project project = createTestProject("Test Project");
        Project saved = projectRepository.save(project);

        boolean exists = projectRepository.existsById(saved.getId());

        assertThat(exists).isTrue();
    }

    private Project createTestProject(String name) {
        return Project.builder()
                .name(name)
                .description("Test Description")
                .startDate(LocalDate.now().plusDays(1))
                .endDate(LocalDate.now().plusDays(30))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}

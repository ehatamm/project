package com.example.project.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    @Test
    void testProjectCreationWithAllArgsConstructor() {
        LocalDate startDate = LocalDate.of(2025, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 31);
        
        Project project = new Project(1L, "Test Project", "Test Description", startDate, endDate, null, null);
        
        assertEquals(1L, project.getId());
        assertEquals("Test Project", project.getName());
        assertEquals("Test Description", project.getDescription());
        assertEquals(startDate, project.getStartDate());
        assertEquals(endDate, project.getEndDate());
    }

    @Test
    void testProjectCreationWithNoArgsConstructor() {
        Project project = new Project();
        LocalDate startDate = LocalDate.of(2025, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 31);
        
        project.setId(1L);
        project.setName("Updated Project");
        project.setDescription("Updated Description");
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        
        assertEquals(1L, project.getId());
        assertEquals("Updated Project", project.getName());
        assertEquals("Updated Description", project.getDescription());
        assertEquals(startDate, project.getStartDate());
        assertEquals(endDate, project.getEndDate());
    }

    @Test
    void testProjectBuilderPattern() {
        LocalDate startDate = LocalDate.of(2025, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 31);
        
        Project project = Project.builder()
                .id(1L)
                .name("Builder Project")
                .description("Built with Lombok builder")
                .startDate(startDate)
                .endDate(endDate)
                .build();
        
        assertEquals(1L, project.getId());
        assertEquals("Builder Project", project.getName());
        assertEquals("Built with Lombok builder", project.getDescription());
        assertEquals(startDate, project.getStartDate());
        assertEquals(endDate, project.getEndDate());
    }

    @Test
    void testProjectBuilderPatternPartial() {
        LocalDate startDate = LocalDate.of(2025, 1, 1);
        
        Project project = Project.builder()
                .name("Partial Builder Project")
                .startDate(startDate)
                .build();
        
        assertNull(project.getId());
        assertEquals("Partial Builder Project", project.getName());
        assertNull(project.getDescription());
        assertEquals(startDate, project.getStartDate());
        assertNull(project.getEndDate());
    }

    @Test
    void testProjectEqualsAndHashCode() {
        LocalDate startDate = LocalDate.of(2025, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 31);
        
        Project project1 = Project.builder()
                .id(1L)
                .name("Test Project")
                .description("Test Description")
                .startDate(startDate)
                .endDate(endDate)
                .build();
        
        Project project2 = Project.builder()
                .id(1L)
                .name("Test Project")
                .description("Test Description")
                .startDate(startDate)
                .endDate(endDate)
                .build();
        
        // Test equals (excludes createdAt and updatedAt)
        assertEquals(project1, project2);
        assertEquals(project1.hashCode(), project2.hashCode());
        
        // Test toString
        String toString = project1.toString();
        assertTrue(toString.contains("Test Project"));
        assertTrue(toString.contains("Test Description"));
    }

    @Test
    void testProjectBuilderWithAllFields() {
        LocalDate startDate = LocalDate.of(2025, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 31);
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        
        Project project = Project.builder()
                .id(1L)
                .name("Complete Builder Test")
                .description("Testing all fields with builder")
                .startDate(startDate)
                .endDate(endDate)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
        
        assertEquals(1L, project.getId());
        assertEquals("Complete Builder Test", project.getName());
        assertEquals("Testing all fields with builder", project.getDescription());
        assertEquals(startDate, project.getStartDate());
        assertEquals(endDate, project.getEndDate());
        assertEquals(createdAt, project.getCreatedAt());
        assertEquals(updatedAt, project.getUpdatedAt());
    }
}

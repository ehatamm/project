package com.example.project.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    @Test
    void testProjectCreation() {
        LocalDate startDate = LocalDate.of(2025, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 31);
        
        Project project = new Project("Test Project", "Test Description", startDate, endDate);
        
        assertNull(project.getId());
        assertEquals("Test Project", project.getName());
        assertEquals("Test Description", project.getDescription());
        assertEquals(startDate, project.getStartDate());
        assertEquals(endDate, project.getEndDate());
    }

    @Test
    void testProjectSetters() {
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
}

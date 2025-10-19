package com.example.project.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record ProjectCreateDto(
    @NotBlank(message = "Project name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    String name,
    
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    String description,
    
    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be today or in the future")
    LocalDate startDate,
    
    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    LocalDate endDate
) {
    @AssertTrue(message = "End date must be after start date")
    public boolean isValidDateRange() {
        return endDate == null || startDate == null || endDate.isAfter(startDate);
    }
}

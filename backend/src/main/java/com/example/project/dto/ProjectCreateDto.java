package com.example.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Schema(description = "Data transfer object for creating a new project")
public record ProjectCreateDto(
    @Schema(description = "Name of the project", example = "E-commerce Platform", required = true)
    @NotBlank(message = "Project name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    String name,
    
    @Schema(description = "Detailed description of the project", example = "A comprehensive e-commerce platform with user management and payment processing")
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    String description,
    
    @Schema(description = "Project start date", example = "2024-01-15", required = true)
    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be today or in the future")
    LocalDate startDate,
    
    @Schema(description = "Project end date", example = "2024-06-30", required = true)
    @NotNull(message = "End date is required")
    @FutureOrPresent(message = "End date must be today or in the future")
    LocalDate endDate
) {
    @AssertTrue(message = "End date must be after start date")
    public boolean isValidDateRange() {
        return endDate == null || startDate == null || endDate.isAfter(startDate);
    }
}

package com.example.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;

@Schema(description = "Project data transfer object")
@Builder
public record ProjectDto(
    @Schema(description = "Unique identifier of the project", example = "1")
    Long id,
    
    @Schema(description = "Name of the project", example = "E-commerce Platform")
    String name,
    
    @Schema(description = "Detailed description of the project", example = "A comprehensive e-commerce platform with user management and payment processing")
    String description,
    
    @Schema(description = "Project start date", example = "2024-01-15")
    LocalDate startDate,
    
    @Schema(description = "Project end date", example = "2024-06-30")
    LocalDate endDate
) {}

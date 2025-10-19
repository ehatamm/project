package com.example.project.dto;

import lombok.Builder;
import java.time.LocalDate;

@Builder
public record ProjectDto(
    Long id,
    String name,
    String description,
    LocalDate startDate,
    LocalDate endDate
) {}

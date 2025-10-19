package com.example.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Map;

/**
 * DTO representing RFC7807 Problem+JSON error response format.
 * This provides proper OpenAPI documentation for error responses.
 */
@Schema(description = "RFC7807 Problem+JSON error response")
public record ProblemDetailDto(
    @Schema(description = "Error type URI", example = "/problems/validation-error")
    String type,
    
    @Schema(description = "Error title", example = "Validation Error")
    String title,

    @Schema(description = "HTTP status code", example = "400")
    Integer status,

    @Schema(description = "Error detail message", example = "Validation failed for one or more fields")
    String detail,
    
    @Schema(description = "Request path", example = "/api/projects")
    String path,
    
    @Schema(description = "Timestamp when error occurred", example = "2024-01-15T10:30:00")
    String timestamp,
    
    @Schema(description = "Field-specific validation errors (only present for validation errors)")
    Map<String, String> fieldErrors
) {}

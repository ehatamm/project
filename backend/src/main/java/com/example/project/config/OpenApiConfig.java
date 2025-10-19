package com.example.project.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Project Management API")
                        .description("A comprehensive REST API for managing projects with full CRUD operations. " +
                                   "All error responses follow RFC7807 Problem+JSON format.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Project Team")
                                .email("team@example.com")
                                .url("https://example.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Development server"),
                        new Server()
                                .url("https://api.example.com")
                                .description("Production server")
                ))
                .components(new io.swagger.v3.oas.models.Components()
                        .addResponses("ValidationError", new ApiResponse()
                                .description("Validation error response")
                                .content(new Content()
                                        .addMediaType("application/problem+json", new MediaType()
                                                .example("""
                                                    {
                                                        "type": "/problems/validation-error",
                                                        "title": "Validation Error",
                                                        "status": 400,
                                                        "detail": "Validation failed for one or more fields",
                                                        "path": "/api/projects",
                                                        "timestamp": "2024-01-15T10:30:00",
                                                        "fieldErrors": {
                                                            "name": "Project name is required",
                                                            "endDate": "End date must be after start date"
                                                        }
                                                    }
                                                    """))))
                        .addResponses("NotFoundError", new ApiResponse()
                                .description("Resource not found error response")
                                .content(new Content()
                                        .addMediaType("application/problem+json", new MediaType()
                                                .example("""
                                                    {
                                                        "type": "/problems/project-not-found",
                                                        "title": "Project Not Found",
                                                        "status": 404,
                                                        "detail": "Project not found with id: 999",
                                                        "path": "/api/projects/999",
                                                        "timestamp": "2024-01-15T10:30:00"
                                                    }
                                                    """))))
                        .addResponses("InternalError", new ApiResponse()
                                .description("Internal server error response")
                                .content(new Content()
                                        .addMediaType("application/problem+json", new MediaType()
                                                .example("""
                                                    {
                                                        "type": "/problems/internal-error",
                                                        "title": "Internal Server Error",
                                                        "status": 500,
                                                        "detail": "An unexpected error occurred",
                                                        "path": "/api/projects",
                                                        "timestamp": "2024-01-15T10:30:00"
                                                    }
                                                    """)))));
    }
}

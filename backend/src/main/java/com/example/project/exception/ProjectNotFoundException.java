package com.example.project.exception;

/**
 * Exception thrown when a project is not found.
 * This provides better error context than generic RuntimeException.
 */
public class ProjectNotFoundException extends RuntimeException {
    
    public ProjectNotFoundException(Long id) {
        super("Project not found with id: " + id);
    }
    
    public ProjectNotFoundException(String message) {
        super(message);
    }
    
    public ProjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

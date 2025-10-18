-- Seed data for projects table
-- This migration adds sample project data for testing and demonstration

INSERT INTO projects (name, description, start_date, end_date, created_at, updated_at) VALUES
('E-commerce Platform Redesign', 'Complete redesign of the main e-commerce platform with modern UI/UX and improved performance', '2024-01-15', '2024-06-30', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Mobile App Development', 'Native mobile application for iOS and Android platforms with offline capabilities', '2024-02-01', '2024-08-15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('API Gateway Implementation', 'Centralized API gateway with authentication, rate limiting, and monitoring', '2024-03-10', '2024-05-20', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Data Analytics Dashboard', 'Real-time analytics dashboard with interactive charts and reporting features', '2024-01-20', '2024-04-30', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Security Audit & Hardening', 'Comprehensive security audit and implementation of security best practices', '2024-02-15', '2024-03-31', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Microservices Migration', 'Migration from monolithic architecture to microservices with containerization', '2024-04-01', '2024-09-30', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Performance Optimization', 'Database and application performance optimization with caching strategies', '2024-03-01', '2024-04-15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('DevOps Pipeline Setup', 'CI/CD pipeline implementation with automated testing and deployment', '2024-01-10', '2024-02-28', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

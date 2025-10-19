# Project Data Model

## Project Entity

The application manages projects with the following fields:

### API Response Fields (ProjectDto)
- `id`: Unique identifier (auto-generated)
- `name`: Project name (required, 3-100 characters)
- `description`: Project description (optional, max 1000 characters)
- `startDate`: Project start date (required, today or future)
- `endDate`: Project end date (required, future date)

### Internal Entity Fields (hidden from API)
- `createdAt`: Audit timestamp (auto-generated)
- `updatedAt`: Audit timestamp (auto-updated)

**Note**: The API uses DTOs to hide internal audit fields from external consumers.

## Validation Rules

- **Name**: Required, 3-100 characters (not blank)
- **Description**: Optional, max 1000 characters  
- **Start Date**: Required, today or future (not null, @FutureOrPresent)
- **End Date**: Required, future date (not null, @Future)
- **Date Range**: End date must be after start date (custom validation)

## DTOs

### ProjectDto (Response)
Used for all GET responses and contains all public fields.

### ProjectCreateDto (Request)
Used for POST requests to create new projects.

### ProjectUpdateDto (Request)
Used for PUT requests to update existing projects.

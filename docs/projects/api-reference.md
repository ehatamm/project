# API Reference

## Endpoints

- `GET /api/projects` - Get all projects (returns ProjectDto array)
- `GET /api/projects/{id}` - Get project by ID (returns ProjectDto)
- `POST /api/projects` - Create new project (accepts ProjectCreateDto, returns ProjectDto)
- `PUT /api/projects/{id}` - Update project (accepts ProjectUpdateDto, returns ProjectDto)
- `DELETE /api/projects/{id}` - Delete project (returns 204 No Content)

## API Documentation

The API includes comprehensive OpenAPI/Swagger documentation:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

The documentation includes:
- Complete endpoint descriptions with examples
- Request/response schemas with validation rules
- Interactive testing interface
- Error response documentation
- Data model descriptions

## Error Responses

- `400 Bad Request`: Validation errors with detailed messages
- `404 Not Found`: Project not found
- `500 Internal Server Error`: Server errors

## Example Requests

### Create Project
```bash
curl -X POST http://localhost:8080/api/projects \
  -H "Content-Type: application/json" \
  -d '{
    "name": "My Project",
    "description": "Project description",
    "startDate": "2024-01-01",
    "endDate": "2024-12-31"
  }'
```

### Get All Projects
```bash
curl http://localhost:8080/api/projects
```

### Update Project
```bash
curl -X PUT http://localhost:8080/api/projects/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Updated Project Name",
    "description": "Updated description"
  }'
```

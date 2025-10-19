# Project Management Application

Full-stack application for managing projects with a REST API backend and modern web frontend.

## Architecture

- **Backend**: Java 21 + Spring Boot 3.5.6 + PostgreSQL 16 + Flyway
- **Frontend**: Nuxt 3 + TypeScript
- **Database**: PostgreSQL 16-alpine
- **Deployment**: Docker Compose

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

## Directory Structure

```
project/
├── backend/          # Spring Boot API
│   ├── src/
│   ├── pom.xml
│   ├── Dockerfile
│   ├── docker-compose.yml
│   └── .env
├── frontend/         # Nuxt 3 application
│   ├── pages/
│   ├── composables/
│   ├── types/
│   ├── nuxt.config.ts
│   ├── package.json
│   ├── Dockerfile
│   ├── docker-compose.yml
│   └── .env
└── infra/            # Infrastructure orchestration
    └── master-compose.yml
```

## Running the Application

### Quick Start (Recommended)

Use the provided startup script for the easiest experience:

```bash
./start.sh
```

This script will:
- Check for Docker and Docker Compose installation
- Detect port conflicts
- Automatically detect dependency changes and force rebuilds when needed
- Start all services with proper dependency handling
- Verify service health and endpoint availability
- Provide troubleshooting guidance if needed

Stop all services:

```bash
docker compose -f infra/master-compose.yml down
```

### Manual Startup

Start all services manually:

```bash
docker compose -f infra/master-compose.yml up -d
```

### Running Services Individually

#### Backend Only
```bash
cd backend
docker compose up -d
```

#### Frontend Only
```bash
cd frontend
docker compose up -d
```

## Access Points

- **Frontend**: http://localhost:3000
- **Backend API**: http://localhost:8080
- **Database**: localhost:5432

## API Endpoints

- `GET /api/projects` - Get all projects (returns ProjectDto array)
- `GET /api/projects/{id}` - Get project by ID (returns ProjectDto)
- `POST /api/projects` - Create new project (accepts ProjectCreateDto, returns ProjectDto)
- `PUT /api/projects/{id}` - Update project (accepts ProjectUpdateDto, returns ProjectDto)
- `DELETE /api/projects/{id}` - Delete project (returns 204 No Content)

### Validation Rules
- **Name**: Required, 3-100 characters
- **Description**: Optional, max 1000 characters  
- **Start Date**: Required, today or future
- **End Date**: Required, future date
- **Date Range**: End date must be after start date

### Error Responses
- `400 Bad Request`: Validation errors with detailed messages
- `404 Not Found`: Project not found
- `500 Internal Server Error`: Server errors

## Development

### Backend Development

```bash
cd backend
./mvnw spring-boot:run
```

### Frontend Development

```bash
cd frontend
npm install
npm run dev
```

## Environment Variables

### Backend (.env)
```
DB_HOST=db
DB_PORT=5432
DB_NAME=projectdb
DB_USER=postgres
DB_PASSWORD=postgres
```

### Frontend (.env)
```
API_BASE_URL=http://localhost:8080
```

## Troubleshooting

If you encounter issues starting the application:

1. **Check service status**:
   ```bash
   docker compose -f infra/master-compose.yml ps
   ```

2. **View service logs**:
   ```bash
   docker compose -f infra/master-compose.yml logs
   ```

3. **Check specific service logs**:
   ```bash
   docker compose -f infra/master-compose.yml logs api
   docker compose -f infra/master-compose.yml logs db
   docker compose -f infra/master-compose.yml logs web
   ```

4. **Test endpoints manually**:
   ```bash
   curl http://localhost:8080/api/projects
   curl http://localhost:3000
   ```

5. **Reset everything**:
   ```bash
   docker compose -f infra/master-compose.yml down -v
   docker system prune -a
   ./start.sh
   ```

For detailed troubleshooting steps, see [TROUBLESHOOTING.md](TROUBLESHOOTING.md).
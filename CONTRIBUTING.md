# Contributing Guide

Thank you for your interest in contributing to the Project Management Application!

## Development Setup

### Prerequisites

- Java 21 or higher
- Maven 3.9+
- Node.js 20+
- Docker and Docker Compose
- PostgreSQL 16 (for local development without Docker)

### Backend Development

1. Navigate to the backend directory:
   ```bash
   cd backend
   ```

2. Install dependencies and build:
   ```bash
   mvn clean install
   ```

3. Run tests:
   ```bash
   mvn test
   ```

4. Run the application locally:
   ```bash
   mvn spring-boot:run
   ```

The backend API will be available at `http://localhost:8080`.

### Frontend Development

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Run the development server:
   ```bash
   npm run dev
   ```

The frontend will be available at `http://localhost:3000`.

### Database Setup

For local development without Docker, set up PostgreSQL:

1. Create a database:
   ```sql
   CREATE DATABASE projectdb;
   ```

2. Update `backend/src/main/resources/application.properties` with your local database credentials.

## Project Structure

### Backend (Java/Spring Boot)

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/example/project/
│   │   │   ├── entity/         # JPA entities
│   │   │   ├── repository/     # Spring Data repositories
│   │   │   ├── service/        # Business logic
│   │   │   └── controller/     # REST controllers
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       ├── java/               # Unit and integration tests
│       └── resources/
│           └── application.properties  # Test configuration
└── pom.xml
```

### Frontend (Nuxt/TypeScript)

```
frontend/
├── pages/                      # Nuxt pages (routes)
│   ├── index.vue              # Project list
│   └── projects/
│       ├── create.vue         # Create project
│       └── [id]/
│           └── edit.vue       # Edit project
├── composables/               # Reusable composition functions
│   └── useProjectApi.ts      # API client
├── types/                     # TypeScript type definitions
│   └── project.ts
└── nuxt.config.ts            # Nuxt configuration
```

## Coding Standards

### Backend

- Follow Java naming conventions
- Use meaningful variable and method names
- Add JavaDoc comments for public methods
- Write unit tests for all business logic
- Keep controllers thin, move logic to services

### Frontend

- Use TypeScript for type safety
- Follow Vue 3 Composition API patterns
- Use semantic HTML
- Keep components focused and reusable
- Use async/await for asynchronous operations

## Testing

### Backend Tests

Run all tests:
```bash
cd backend
mvn test
```

Run specific test:
```bash
mvn test -Dtest=ProjectTest
```

### Frontend Tests

Currently, the frontend does not have automated tests. Consider adding:
- Component tests with Vitest
- E2E tests with Playwright

## Docker Development

### Build and run all services:
```bash
docker compose -f infra/master-compose.yml up --build
```

### View logs:
```bash
docker compose -f infra/master-compose.yml logs -f
```

### Stop services:
```bash
docker compose -f infra/master-compose.yml down
```

## Making Changes

1. Create a feature branch
2. Make your changes
3. Write/update tests
4. Ensure all tests pass
5. Submit a pull request

## API Documentation

### Project Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/projects | Get all projects |
| GET | /api/projects/{id} | Get project by ID |
| POST | /api/projects | Create new project |
| PUT | /api/projects/{id} | Update project |
| DELETE | /api/projects/{id} | Delete project |

### Project Entity

```json
{
  "id": 1,
  "name": "Project Name",
  "description": "Project Description",
  "startDate": "2025-01-01",
  "endDate": "2025-12-31"
}
```

## Troubleshooting

### Backend won't start

- Check if PostgreSQL is running
- Verify database credentials in application.properties
- Ensure port 8080 is not in use

### Frontend won't start

- Clear node_modules and reinstall: `rm -rf node_modules && npm install`
- Check if port 3000 is not in use
- Verify API_BASE_URL in .env

### Docker issues

- Ensure Docker daemon is running
- Check if ports 3000, 8080, and 5432 are available
- Try rebuilding: `docker compose -f infra/master-compose.yml up --build --force-recreate`

## Questions?

Feel free to open an issue for any questions or concerns!

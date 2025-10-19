# Project Architecture

## Technology Stack

- **Backend**: Java 21 + Spring Boot 3.5.6 + PostgreSQL 16 + Flyway
- **Frontend**: Nuxt 3 + TypeScript
- **Database**: PostgreSQL 16-alpine
- **Deployment**: Docker Compose

## Directory Structure

```
project/
├── backend/          # Spring Boot API
│   ├── src/
│   ├── pom.xml
│   ├── docker/
│   │   └── Dockerfile
│   ├── docker-compose.yml
│   └── Dockerfile.dev
├── frontend/         # Nuxt 3 application
│   ├── pages/
│   ├── composables/
│   ├── types/
│   ├── nuxt.config.ts
│   ├── package.json
│   ├── docker/
│   │   └── Dockerfile
│   ├── docker-compose.yml
│   └── Dockerfile.dev
├── docs/             # Project documentation
│   └── projects/
├── docker-compose.yml # Main orchestration file
```

## Service Architecture

The application uses Docker Compose to orchestrate three services:

1. **PostgreSQL Database** - Stores application data
2. **Spring Boot API** - REST API backend
3. **Nuxt Frontend** - Vue.js frontend application

All services are containerized and configured to work together seamlessly.

## Access Points

- **Frontend**: http://localhost:3000
- **Backend API**: http://localhost:8080
- **Database**: localhost:5432
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

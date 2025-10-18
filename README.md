# Project Management Application

Full-stack application for managing projects with a REST API backend and modern web frontend.

## Architecture

- **Backend**: Java 21 + Spring Boot 3.3 + PostgreSQL 16
- **Frontend**: Nuxt 3 + TypeScript
- **Database**: PostgreSQL 16-alpine
- **Deployment**: Docker Compose

## Project Entity

The application manages projects with the following fields:
- `id`: Unique identifier (auto-generated)
- `name`: Project name (required)
- `description`: Project description
- `startDate`: Project start date
- `endDate`: Project end date

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

### Using Master Compose (Recommended)

Start all services at once from the project root:

```bash
docker compose -f infra/master-compose.yml up -d
```

Stop all services:

```bash
docker compose -f infra/master-compose.yml down
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

- `GET /api/projects` - Get all projects
- `GET /api/projects/{id}` - Get project by ID
- `POST /api/projects` - Create new project
- `PUT /api/projects/{id}` - Update project
- `DELETE /api/projects/{id}` - Delete project

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
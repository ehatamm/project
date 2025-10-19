# Development Guide

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
API_BASE_URL=http://api:8080
```

## Running Services Individually

### Backend Development

```bash
cd backend
./mvnw spring-boot:run
```

Or with Docker:
```bash
cd backend
docker compose up -d
```

### Frontend Development

```bash
cd frontend
npm install
npm run dev
```

Or with Docker:
```bash
cd frontend
docker compose up -d
```

## Manual Startup

Start all services manually:
```bash
docker compose up -d
```

Stop all services:
```bash
docker compose down
```

## Testing Endpoints

Test endpoints manually:
```bash
curl http://localhost:8080/api/projects
curl http://localhost:3000
```

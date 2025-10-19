# Project Development Environment

## Quick Start

To run the entire stack locally with one command:

```bash
docker compose up -d
```

## Services

- **Backend API**: http://localhost:8080
- **Frontend**: http://localhost:3000  
- **Database**: localhost:5432

## Architecture

- **Spring Boot** backend with PostgreSQL
- **Nuxt 3** frontend
- **PostgreSQL** database

## Development

The application uses Docker Compose to orchestrate three services:

1. **PostgreSQL Database** - Stores application data
2. **Spring Boot API** - REST API backend
3. **Nuxt Frontend** - Vue.js frontend application

All services are containerized and configured to work together seamlessly.

## Stopping Services

```bash
docker compose down
```
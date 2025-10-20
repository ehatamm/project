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

## Frontend Architecture

### Dependencies
The frontend includes:
- **Nuxt 3** - Vue.js framework
- **Vuetify 3** - Material Design component library
- **Material Design Icons** - Icon system
- **TypeScript** - Type safety

### Component Structure
- **Pages**: Main application pages (`pages/index.vue`)
- **Components**: Reusable Vuetify components in `components/`
- **Composables**: Business logic in `composables/`
- **Types**: TypeScript definitions in `types/`
- **Plugins**: Vuetify configuration in `plugins/`

### Development Patterns
- **Composables**: Use for business logic and state management
- **Components**: Vuetify-based UI components with proper props/emits
- **Error Handling**: Global error management via `useErrorHandler`
- **API Layer**: Centralized API calls via `useProjectApi`

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

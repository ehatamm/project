# Project Architecture

## Technology Stack

- **Backend**: Java 21 + Spring Boot 3.5.6 + PostgreSQL 16 + Flyway
- **Frontend**: Nuxt 3 + TypeScript + Vuetify 3 + Material Design Icons
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
├── frontend/         # Nuxt 3 application with Vuetify
│   ├── components/   # Vuetify-based UI components
│   ├── composables/  # Vue 3 composables for business logic
│   ├── plugins/      # Vuetify plugin configuration
│   ├── types/        # TypeScript type definitions
│   ├── pages/
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
3. **Nuxt Frontend** - Vue.js frontend application with Vuetify UI framework

All services are containerized and configured to work together seamlessly.

## Frontend Architecture

### Component Structure
- **Pages**: Main application pages (`index.vue`)
- **Components**: Reusable Vuetify-based components
  - `DataTable.vue` - Generic data table component
  - `ProjectTable.vue` - Project-specific table with loading states
  - `ProjectFormVuetify.vue` - Form component with validation
  - `TableActions.vue` - Action buttons for table rows
  - `ErrorSnackbar.vue` - Global error notification component

### Composables
- **`useProjectManagement.ts`** - Centralized business logic for project operations
- **`useProjectApi.ts`** - API layer with comprehensive error handling
- **`useErrorHandler.ts`** - Global error management and display

### UI Framework
- **Vuetify 3** - Material Design component library
- **Material Design Icons** - Icon system
- **Light Theme** - Professional light theme with semantic colors
- **Responsive Design** - Mobile-first responsive layout

## Access Points

- **Frontend**: http://localhost:3000
- **Backend API**: http://localhost:8080
- **Database**: localhost:5432
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

# Frontend Architecture Guide

## Overview

The frontend is built with **Nuxt 3** and **Vuetify 3**, providing a modern, responsive Material Design interface for project management.

## Technology Stack

- **Nuxt 3** - Vue.js framework with SSR capabilities
- **Vuetify 3** - Material Design component library
- **TypeScript** - Type safety and better developer experience
- **Material Design Icons** - Comprehensive icon system

## Project Structure

```
frontend/
├── components/           # Reusable UI components
│   ├── DataTable.vue    # Generic data table component
│   ├── ProjectTable.vue # Project-specific table with states
│   ├── ProjectFormVuetify.vue # Form with validation
│   ├── TableActions.vue # Edit/delete action buttons
│   └── ErrorSnackbar.vue # Global error notifications
├── composables/          # Business logic and state management
│   ├── useProjectManagement.ts # Main business logic
│   ├── useProjectApi.ts  # API layer with error handling
│   └── useErrorHandler.ts # Global error management
├── plugins/             # Nuxt plugins
│   └── vuetify.client.ts # Vuetify configuration
├── types/               # TypeScript definitions
│   └── project.ts       # Project interface
├── pages/               # Application pages
│   └── index.vue        # Main dashboard page
└── app.vue              # Root application component
```

## Component Architecture

### Pages
- **`index.vue`** - Main dashboard with project table and modals

### Components
- **`DataTable.vue`** - Generic, reusable data table component
- **`ProjectTable.vue`** - Project-specific table with loading/empty states
- **`ProjectFormVuetify.vue`** - Form component with comprehensive validation
- **`TableActions.vue`** - Action buttons for table rows (edit/delete)
- **`ErrorSnackbar.vue`** - Global error notification component

### Composables
- **`useProjectManagement.ts`** - Centralized business logic for all project operations
- **`useProjectApi.ts`** - API layer with comprehensive error handling and response parsing
- **`useErrorHandler.ts`** - Global error management and display system

## UI Framework

### Vuetify Configuration
- **Light Theme** - Professional light theme with semantic colors
- **Material Design Icons** - Comprehensive icon system
- **Responsive Design** - Mobile-first responsive layout
- **Component Library** - Pre-built Material Design components

### Theme Colors
- **Primary**: `#1976D2` (Blue)
- **Secondary**: `#424242` (Dark Gray)
- **Success**: `#4CAF50` (Green)
- **Error**: `#FF5252` (Red)
- **Warning**: `#FFC107` (Amber)
- **Info**: `#2196F3` (Light Blue)

## Development Patterns

### Composables Usage
```typescript
// Business logic in composables
const { projects, loading, handleCreate } = useProjectManagement()

// API calls with error handling
const { getAllProjects, createProject } = useProjectApi()

// Global error management
const { showErrorMessage } = useErrorHandler()
```

### Component Communication
- **Props Down** - Parent components pass data to children
- **Events Up** - Child components emit events to parents
- **Composables** - Shared state and logic across components

### Error Handling
- **Global Error Display** - Errors shown via `ErrorSnackbar` component
- **API Error Parsing** - Comprehensive error message extraction
- **User-Friendly Messages** - Clear, actionable error messages

## State Management

### Local State
- Component-level state using Vue 3 `ref()` and `reactive()`
- Form state managed within form components
- Modal state managed in parent components

### Shared State
- Project data managed via `useProjectManagement` composable
- Error state managed via `useErrorHandler` composable
- Loading states managed per operation

## API Integration

### Error Handling
- Comprehensive API error parsing with field-specific messages
- RFC7807 Problem+JSON format support
- User-friendly error message display

### Data Flow
1. User action triggers composable method
2. Composable calls API layer
3. API layer handles request/response
4. Success: Update local state
5. Error: Display via global error handler

## Responsive Design

### Breakpoints
- **Mobile**: < 600px
- **Tablet**: 600px - 960px
- **Desktop**: > 960px

### Layout Components
- **v-container** - Responsive container with max-width
- **v-row/v-col** - Grid system for responsive layouts
- **v-responsive** - Component-level responsive behavior

## Performance Considerations

### Code Splitting
- Automatic code splitting by Nuxt 3
- Lazy loading of components when needed
- Optimized bundle sizes

### Loading States
- Skeleton loaders for better perceived performance
- Progress indicators for long operations
- Disabled states during operations

## Development Workflow

### Adding New Features
1. Define TypeScript interfaces in `types/`
2. Create API methods in `useProjectApi.ts`
3. Add business logic to `useProjectManagement.ts`
4. Create UI components in `components/`
5. Integrate into pages

### Styling Guidelines
- Use Vuetify components and classes
- Follow Material Design principles
- Maintain consistent spacing and typography
- Use semantic color tokens (primary, secondary, etc.)

# Troubleshooting Guide

## Quick Start

1. **Start the application**: `./start.sh`
2. **If it fails**: Follow the troubleshooting steps below

## Common Issues & Solutions

### 1. Port Conflicts

**Symptoms**: Services fail to start with "port already in use" errors

**Solution**:
```bash
# Check what's using the ports
lsof -i :5432  # PostgreSQL
lsof -i :8080  # API
lsof -i :3000  # Frontend

# Stop conflicting services or change ports in docker-compose.yml
```

### 2. Docker Build Failures

**Symptoms**: Build process hangs or fails

**Solutions**:
```bash
# Clean Docker cache
docker system prune -a

# Rebuild without cache
docker compose -f infra/master-compose.yml build --no-cache

# Check Docker resources
docker system df
```

### 3. Database Connection Issues

**Symptoms**: API fails to start, database connection errors

**Check**:
```bash
# Database logs
docker compose -f infra/master-compose.yml logs db

# Database status
docker compose -f infra/master-compose.yml exec db pg_isready -U postgres

# Test connection
docker compose -f infra/master-compose.yml exec db psql -U postgres -d projectdb -c "SELECT 1;"
```

### 4. API Service Issues

**Symptoms**: API container exits or fails health checks

**Check**:
```bash
# API logs
docker compose -f infra/master-compose.yml logs api

# Check if API is responding
curl http://localhost:8080/api/projects

# Check API container status
docker compose -f infra/master-compose.yml ps api
```

### 5. Frontend Build Issues

**Symptoms**: Frontend container fails to build or start

**Check**:
```bash
# Frontend logs
docker compose -f infra/master-compose.yml logs web

# Check Node.js version compatibility
docker compose -f infra/master-compose.yml exec web node --version
```

## Diagnostic Commands

### Service Status
```bash
# Check all services
docker compose -f infra/master-compose.yml ps

# Check specific service
docker compose -f infra/master-compose.yml ps api
```

### Logs
```bash
# All services
docker compose -f infra/master-compose.yml logs

# Specific service (last 50 lines)
docker compose -f infra/master-compose.yml logs --tail=50 api

# Follow logs in real-time
docker compose -f infra/master-compose.yml logs -f api
```

### Health Checks
```bash
# Database health
docker compose -f infra/master-compose.yml exec db pg_isready -U postgres

# API health
curl -f http://localhost:8080/api/projects || echo "API not responding"

# Frontend health
curl -f http://localhost:3000 || echo "Frontend not responding"
```

## Reset Everything

If all else fails:

```bash
# Stop all services
docker compose -f infra/master-compose.yml down

# Remove volumes (WARNING: This deletes all data)
docker compose -f infra/master-compose.yml down -v

# Remove all containers and images
docker compose -f infra/master-compose.yml down --rmi all

# Clean Docker system
docker system prune -a

# Start fresh
./start.sh
```

## Performance Issues

### Slow Startup
- **First run**: 2-3 minutes is normal (building images)
- **Subsequent runs**: Should be 30-60 seconds
- **Code changes only**: Should be 10-20 seconds

### Memory Issues
```bash
# Check Docker resource usage
docker stats

# Increase Docker memory limit in Docker Desktop settings
```

## Environment Variables

If you need to override default settings:

```bash
# Create .env file in project root
DB_HOST=localhost
DB_PORT=5432
DB_NAME=projectdb
DB_USER=postgres
DB_PASSWORD=postgres
API_BASE_URL=http://localhost:8080
```

## Getting Help

1. **Check logs first**: Most issues are visible in the logs
2. **Verify prerequisites**: Docker and Docker Compose installed
3. **Check system resources**: Enough memory and disk space
4. **Try reset**: Clean slate approach often works

## Expected Behavior

### Successful Startup
```
✓ Docker is installed
✓ Docker Compose is installed
Starting all services...
Waiting for services to initialize...
Checking service health...

======================================
Application Started Successfully!
======================================

Access Points:
  - Frontend:  http://localhost:3000
  - Backend:   http://localhost:8080
  - Database:  localhost:5432
```

### Service Status (docker compose ps)
```
NAME           IMAGE                    STATUS
project-api    project-api:latest       Up (healthy)
project-db      postgres:16-alpine      Up (healthy)
project-web    project-web:latest       Up
```

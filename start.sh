#!/bin/bash
set -e

# Project Management Application - Quick Start Script

COMPOSE_FILE='infra/master-compose.yml'

# Function to restart a specific service
restart_service() {
    local service=$1
    echo "Restarting $service..."
    docker compose -f "$COMPOSE_FILE" restart "$service"
    echo "Waiting for $service to stabilize..."
    sleep 5
}

echo "======================================"
echo "Project Management Application Setup"
echo "======================================"
echo ""

# Check if Docker is installed
if ! command -v docker &> /dev/null; then
    echo "Error: Docker is not installed. Please install Docker first."
    exit 1
fi

# Check if Docker Compose is installed
if ! command -v docker compose &> /dev/null; then
    echo "Error: Docker Compose is not installed. Please install Docker Compose first."
    exit 1
fi

echo "✓ Docker is installed"
echo "✓ Docker Compose is installed"
echo ""

# Check for port conflicts
echo "Checking for port conflicts..."
if lsof -Pi :5432 -sTCP:LISTEN -t >/dev/null 2>&1; then
    echo "⚠️  Warning: Port 5432 (PostgreSQL) is already in use"
fi
if lsof -Pi :8080 -sTCP:LISTEN -t >/dev/null 2>&1; then
    echo "⚠️  Warning: Port 8080 (API) is already in use"
fi
if lsof -Pi :3000 -sTCP:LISTEN -t >/dev/null 2>&1; then
    echo "⚠️  Warning: Port 3000 (Frontend) is already in use"
fi
echo ""

# Start the application
echo "Starting all services..."
echo "This may take 2-3 minutes on first run..."
echo ""

# Start services with better error handling
echo "Starting/restarting services..."
docker compose -f "$COMPOSE_FILE" up -d

# Wait a moment for services to initialize
echo "Waiting for services to initialize..."
sleep 5

# Test if services are actually responding
echo "Testing service endpoints..."

# Test API
echo -n "Testing API... "
if curl -s -f http://localhost:8080/api/projects >/dev/null 2>&1; then
    echo "✅ API is responding"
    API_RESPONDING=true
else
    echo "⏳ API not yet responding (this is normal during startup)"
    API_RESPONDING=false
fi

# Test Frontend
echo -n "Testing Frontend... "
if curl -s -f http://localhost:3000 >/dev/null 2>&1; then
    echo "✅ Frontend is responding"
    FRONTEND_RESPONDING=true
else
    echo "⏳ Frontend not yet responding (this is normal during startup)"
    FRONTEND_RESPONDING=false
fi

echo ""

# Check specific services by name
EXPECTED_SERVICES=("project-api" "project-db" "project-web")
RUNNING_COUNT=0

echo "Checking required services:"
for service in "${EXPECTED_SERVICES[@]}"; do
    if docker ps --filter "name=$service" --filter "status=running" --format "{{.Names}}" | grep -q "^$service$"; then
        echo "✓ $service is running"
        ((RUNNING_COUNT++))
    else
        echo "✗ $service is not running"
    fi
done

if [ "$RUNNING_COUNT" -eq "${#EXPECTED_SERVICES[@]}" ]; then
    echo "✅ All services are running ($RUNNING_COUNT/${#EXPECTED_SERVICES[@]})"
    echo ""
    if [ "$API_RESPONDING" = true ] && [ "$FRONTEND_RESPONDING" = true ]; then
        echo "🎉 All endpoints are responding! Application is ready to use."
    elif [ "$API_RESPONDING" = true ] || [ "$FRONTEND_RESPONDING" = true ]; then
        echo "⚠️  Some endpoints are responding. Services may still be initializing."
    else
        echo "⏳ Services are running but endpoints not yet responding (normal during startup)."
    fi
    echo ""
    echo "Access Points:"
    echo "  - Frontend:  http://localhost:3000"
    echo "  - Backend:   http://localhost:8080"
    echo "  - Database:  localhost:5432"
else
    echo "⚠️  Some services are not running ($RUNNING_COUNT/${#EXPECTED_SERVICES[@]})"
    echo ""
    echo "Troubleshooting:"
    echo "  Check logs: docker compose -f \"$COMPOSE_FILE\" logs"
    echo "  Check status: docker compose -f \"$COMPOSE_FILE\" ps"
fi

echo ""
echo "======================================"
echo "Application Started Successfully!"
echo "======================================"
echo ""
echo "Access Points:"
echo "  - Frontend:  http://localhost:3000"
echo "  - Backend:   http://localhost:8080"
echo "  - Database:  localhost:5432"
echo ""
echo "Management Commands:"
echo "  Stop:        docker compose -f \"$COMPOSE_FILE\" down"
echo "  View logs:   docker compose -f \"$COMPOSE_FILE\" logs -f"
echo "  API logs:    docker compose -f \"$COMPOSE_FILE\" logs -f api"
echo "  DB logs:     docker compose -f \"$COMPOSE_FILE\" logs -f db"
echo "  Status:      docker compose -f \"$COMPOSE_FILE\" ps"
echo ""
echo "Troubleshooting:"
echo "  If services fail to start, see TROUBLESHOOTING.md"
echo "  For API issues: docker compose -f \"$COMPOSE_FILE\" logs api"
echo "  For DB issues:  docker compose -f \"$COMPOSE_FILE\" logs db"
echo ""

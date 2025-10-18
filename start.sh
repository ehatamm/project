#!/bin/bash

# Project Management Application - Quick Start Script

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

# Start the application
echo "Starting all services..."
echo ""

docker compose -f infra/master-compose.yml up -d

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
echo "To stop the application, run:"
echo "  docker compose -f infra/master-compose.yml down"
echo ""
echo "To view logs:"
echo "  docker compose -f infra/master-compose.yml logs -f"
echo ""

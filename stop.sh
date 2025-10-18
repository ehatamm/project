#!/bin/bash
set -e

# Project Management Application - Stop Script

COMPOSE_FILE='infra/master-compose.yml'

echo "======================================"
echo "Stopping Project Management Application"
echo "======================================"
echo ""

docker compose -f "$COMPOSE_FILE" down

echo ""
echo "Application stopped successfully!"
echo ""

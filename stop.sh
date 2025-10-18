#!/bin/bash

# Project Management Application - Stop Script

echo "======================================"
echo "Stopping Project Management Application"
echo "======================================"
echo ""

docker compose -f infra/master-compose.yml down

echo ""
echo "Application stopped successfully!"
echo ""

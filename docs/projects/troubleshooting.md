# Troubleshooting Guide

## Service Status Check

Check service status:
```bash
docker compose ps
```

## View Service Logs

View all service logs:
```bash
docker compose logs
```

Check specific service logs:
```bash
docker compose logs api
docker compose logs db
docker compose logs web
```

## Common Issues

### Port Conflicts
If you encounter port conflicts:
1. Check what's running on ports 3000, 8080, and 5432
2. Stop conflicting services or change ports in docker-compose files

### Database Connection Issues
1. Ensure PostgreSQL container is running: `docker compose logs db`
2. Check database credentials in backend/.env
3. Verify database is accessible: `docker compose exec db psql -U postgres -d projectdb`

### API Not Responding
1. Check backend logs: `docker compose logs api`
2. Verify API is accessible: `curl http://localhost:8080/api/projects`
3. Check Swagger UI: http://localhost:8080/swagger-ui.html

### Frontend Not Loading
1. Check frontend logs: `docker compose logs web`
2. Verify frontend is accessible: `curl http://localhost:3000`
3. Check if API_BASE_URL is correct in frontend/.env

## Reset Everything

If you need to start fresh:
```bash
docker compose down -v
docker system prune -a
docker compose up -d
```

## Health Checks

Test all endpoints:
```bash
# Test API
curl http://localhost:8080/api/projects

# Test Frontend
curl http://localhost:3000

# Test Database (from within container)
docker compose exec db psql -U postgres -d projectdb -c "SELECT COUNT(*) FROM projects;"
```

For more detailed troubleshooting steps, see [TROUBLESHOOTING.md](../../TROUBLESHOOTING.md).

# PostgreSQL Migration Guide

This guide will help you migrate from H2 in-memory database to PostgreSQL for the User Profile Application.

## Prerequisites

- Docker and Docker Compose installed
- OR PostgreSQL 12+ installed locally

## Option 1: Using Docker (Recommended)

### 1. Start PostgreSQL with Docker Compose

```bash
# Start PostgreSQL and pgAdmin
docker-compose up -d

# Check if containers are running
docker-compose ps
```

### 2. Verify Database Setup

```bash
# Connect to PostgreSQL container
docker exec -it userprofile-postgres psql -U postgres

# List databases (should see userprofiledb, userprofile_dev, userprofile_test, userprofile_prod)
\l

# Connect to userprofiledb
\c userprofiledb

# List tables (will be empty initially, created by Hibernate on first run)
\dt

# Exit
\q
```

### 3. Access pgAdmin (Optional)

- Open http://localhost:8081
- Login: admin@userprofile.com / admin123
- Add server connection:
  - Host: postgres
  - Port: 5432
  - Username: userprofile_user
  - Password: userprofile_password

## Option 2: Local PostgreSQL Installation

### 1. Install PostgreSQL

**macOS (using Homebrew):**
```bash
brew install postgresql
brew services start postgresql
```

**Ubuntu/Debian:**
```bash
sudo apt update
sudo apt install postgresql postgresql-contrib
sudo systemctl start postgresql
sudo systemctl enable postgresql
```

**Windows:**
Download from https://www.postgresql.org/download/windows/

### 2. Setup Database

```bash
# Connect as postgres user
sudo -u postgres psql

# Run the setup script
\i database-setup.sql

# Exit
\q
```

## Running the Application

### 1. Default Profile (PostgreSQL)

```bash
cd user-profile-controller
mvn spring-boot:run
```

### 2. Development Profile

```bash
cd user-profile-controller
mvn spring-boot:run -Dspring.profiles.active=dev
```

### 3. Production Profile

```bash
cd user-profile-controller
mvn spring-boot:run -Dspring.profiles.active=prod
```

### 4. Test Profile

```bash
mvn test
```

## Environment Variables for Production

Set these environment variables for production deployment:

```bash
export DATABASE_URL=jdbc:postgresql://your-db-host:5432/userprofile_prod
export DATABASE_USERNAME=your_username
export DATABASE_PASSWORD=your_password
```

## Database Configuration Details

### Connection Pool Settings (HikariCP)

- **Maximum Pool Size**: 20 connections
- **Minimum Idle**: 5 connections
- **Idle Timeout**: 5 minutes
- **Connection Timeout**: 20 seconds

### JPA/Hibernate Settings

- **DDL Auto**: 
  - `update` for default and dev profiles
  - `validate` for production
  - `create-drop` for test profile
- **Dialect**: PostgreSQLDialect
- **Show SQL**: Enabled for dev, disabled for prod

## Application Configuration

The application is configured to run on port **8087** and includes:

- **Base URL**: http://localhost:8087/user-profile
- **Swagger UI**: http://localhost:8087/user-profile/swagger-ui.html
- **API Endpoints**:
  - POST `/v1/user/create` - Create new user
  - GET `/v1/user/{userId}` - Get user by ID

## Database Schema

The application will automatically create the following table:

### user_details
- `id` (BIGINT, Primary Key, Auto-increment)
- `first_name` (VARCHAR(50), Not Null)
- `last_name` (VARCHAR(50), Not Null)
- `email` (VARCHAR(100), Not Null, Unique)
- `phone_number` (VARCHAR(15))
- `address` (VARCHAR(255))

Sample data is automatically loaded from `data.sql` on application startup.

## Troubleshooting

### Connection Issues

1. **Check PostgreSQL is running:**
   ```bash
   # Docker
   docker-compose ps
   
   # Local installation
   sudo systemctl status postgresql
   ```

2. **Check database exists:**
   ```bash
   docker exec -it userprofile-postgres psql -U postgres -c "\l"
   ```

3. **Check user permissions:**
   ```bash
   docker exec -it userprofile-postgres psql -U userprofile_user -d userprofiledb -c "SELECT current_user;"
   ```

### Performance Tuning

For production, consider these PostgreSQL settings in `postgresql.conf`:

```
shared_buffers = 256MB
effective_cache_size = 1GB
maintenance_work_mem = 64MB
checkpoint_completion_target = 0.9
wal_buffers = 16MB
default_statistics_target = 100
random_page_cost = 1.1
effective_io_concurrency = 200
```

### Backup and Restore

**Backup:**
```bash
docker exec userprofile-postgres pg_dump -U userprofile_user userprofiledb > backup.sql
```

**Restore:**
```bash
docker exec -i userprofile-postgres psql -U userprofile_user userprofiledb < backup.sql
```

## Migration Benefits

✅ **Persistent Data**: Data survives application restarts
✅ **Better Performance**: Optimized for production workloads
✅ **JSON Support**: Native JSONB support for flexible schemas
✅ **Scalability**: Better handling of concurrent users
✅ **ACID Compliance**: Full transaction support
✅ **Rich Indexing**: Advanced indexing capabilities
✅ **Production Ready**: Enterprise-grade database

## Quick Start Commands

```bash
# 1. Start the database
./start-database.sh

# 2. Build the application
mvn clean install

# 3. Run the application
cd user-profile-controller
mvn spring-boot:run

# 4. Test the API
curl -X GET http://localhost:8087/user-profile/v1/user/1
```

## Next Steps

1. Start the database using Docker Compose or the provided script
2. Run the application with the default profile
3. Test the user profile APIs using Swagger UI
4. Monitor performance and adjust connection pool settings as needed
5. Set up regular backups for production

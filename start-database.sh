#!/bin/bash

echo "🚀 Starting PostgreSQL Database for User Profile Application"
echo "============================================================"

# Check if Docker is running
if ! docker info > /dev/null 2>&1; then
    echo "❌ Docker is not running. Please start Docker first."
    exit 1
fi

# Check if docker-compose is available
if ! command -v docker-compose &> /dev/null; then
    echo "❌ docker-compose is not installed. Please install docker-compose first."
    exit 1
fi

echo "📦 Starting PostgreSQL and pgAdmin containers..."
docker-compose up -d

echo "⏳ Waiting for PostgreSQL to be ready..."
sleep 10

# Check if PostgreSQL is ready
until docker exec userprofile-postgres pg_isready -U postgres > /dev/null 2>&1; do
    echo "⏳ Waiting for PostgreSQL to start..."
    sleep 2
done

echo "✅ PostgreSQL is ready!"

# Verify database setup
echo "🔍 Verifying database setup..."
docker exec userprofile-postgres psql -U postgres -c "\l" | grep userprofile

echo ""
echo "🎉 Database setup complete!"
echo ""
echo "📊 Database Information:"
echo "  - PostgreSQL: localhost:5432"
echo "  - Username: userprofile_user"
echo "  - Password: userprofile_password"
echo "  - Databases: userprofiledb, userprofile_dev, userprofile_test, userprofile_prod"
echo ""
echo "🌐 pgAdmin (Optional):"
echo "  - URL: http://localhost:8081"
echo "  - Email: admin@userprofile.com"
echo "  - Password: admin123"
echo ""
echo "🚀 You can now start your Spring Boot application:"
echo "  cd user-profile-controller"
echo "  mvn spring-boot:run"
echo ""
echo "📋 To stop the database:"
echo "  docker-compose down"

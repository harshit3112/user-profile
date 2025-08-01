-- PostgreSQL Database Setup Script for User Profile Application
-- Run this script as a PostgreSQL superuser (e.g., postgres)

-- Create database user
CREATE USER userprofile_user WITH PASSWORD 'userprofile_password';

-- Create databases for different environments
CREATE DATABASE userprofiledb OWNER userprofile_user;
CREATE DATABASE userprofile_dev OWNER userprofile_user;
CREATE DATABASE userprofile_test OWNER userprofile_user;
CREATE DATABASE userprofile_prod OWNER userprofile_user;

-- Grant privileges
GRANT ALL PRIVILEGES ON DATABASE userprofiledb TO userprofile_user;
GRANT ALL PRIVILEGES ON DATABASE userprofile_dev TO userprofile_user;
GRANT ALL PRIVILEGES ON DATABASE userprofile_test TO userprofile_user;
GRANT ALL PRIVILEGES ON DATABASE userprofile_prod TO userprofile_user;

-- Connect to each database and grant schema privileges
\c userprofiledb;
GRANT ALL ON SCHEMA public TO userprofile_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO userprofile_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO userprofile_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO userprofile_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO userprofile_user;

\c userprofile_dev;
GRANT ALL ON SCHEMA public TO userprofile_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO userprofile_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO userprofile_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO userprofile_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO userprofile_user;

\c userprofile_test;
GRANT ALL ON SCHEMA public TO userprofile_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO userprofile_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO userprofile_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO userprofile_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO userprofile_user;

\c userprofile_prod;
GRANT ALL ON SCHEMA public TO userprofile_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO userprofile_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO userprofile_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO userprofile_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO userprofile_user;

-- Create extensions (if needed)
\c userprofiledb;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

\c userprofile_dev;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

\c userprofile_test;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

\c userprofile_prod;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Display created databases
\l

-- Show confirmation message
\echo 'User Profile databases setup completed successfully!'
\echo 'Created databases: userprofiledb, userprofile_dev, userprofile_test, userprofile_prod'
\echo 'Created user: userprofile_user'

# Use the latest MySQL server image
FROM mysql/mysql-server:latest

# Set the working directory
WORKDIR /tmp

# Copy all the SQL and dump files to the working directory
COPY test_db/*.sql /tmp/
COPY test_db/*.dump /tmp/

# Copy the main SQL file to docker-entrypoint-initdb.d
COPY test_db/employees.sql /docker-entrypoint-initdb.d/

# Set the root password and host using the modern ENV syntax
ENV MYSQL_ROOT_PASSWORD="example"
ENV MYSQL_ROOT_HOST="%"
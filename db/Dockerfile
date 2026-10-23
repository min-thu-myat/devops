FROM mysql:8.0

# Copy the employees database script
COPY test_db/employees.sql /docker-entrypoint-initdb.d/

ENV MYSQL_ROOT_PASSWORD=example
ENV MYSQL_DATABASE=employees
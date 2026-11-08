FROM mysql:8.0
# Copy all initialization scripts and dumps into docker-entrypoint-initdb.d
COPY *.sql /docker-entrypoint-initdb.d/
COPY *.dump /docker-entrypoint-initdb.d/
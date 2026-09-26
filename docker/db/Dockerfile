FROM mysql:8.0
ENV MYSQL_ROOT_PASSWORD=nopCommerce_db_password
LABEL org.opencontainers.image.source=https://github.com/anhlyn/mvnNopCommerce
COPY init.sql /docker-entrypoint-initdb.d/init.sql

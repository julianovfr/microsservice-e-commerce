FROM postgres:14.19-alpine3.21

#definindo variaveis de ambiente
ENV POSTGRES_USER=commerce
ENV POSTGRES_PASSWORD=87654321

COPY ./init.sql /docker-entrypoint-initdb.d/

#porta padrao
EXPOSE 5432

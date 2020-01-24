# If you would like to do additional initialization in an image derived from this one, 
# add one or more *.sql, *.sql.gz, or *.sh scripts under /docker-entrypoint-initdb.d 
# (creating the directory if necessary). After the entrypoint calls initdb to create 
# the default postgres user and database, it will run any *.sql files, run any executable 
# *.sh scripts, and source any non-executable *.sh scripts found in that directory to do 
# further initialization before starting the service.

# These initialization files will be executed in sorted name order as defined by the 
# current locale, which defaults to en_US.utf8. Any *.sql files will be executed by 
# POSTGRES_USER, which defaults to the postgres superuser. It is recommended that any 
# psql commands that are run inside of a *.sh script be executed as POSTGRES_USER by 
# using the --username "$POSTGRES_USER" flag. This user will be able to connect without 
# a password due to the presence of trust authentication for Unix socket connections 
# made inside the container.



FROM postgres:12.1

# RUN mkdir /docker-entrypoint-initdb.d
COPY 00_create_schema.sql /docker-entrypoint-initdb.d
COPY 10_insert_test_data.sql /docker-entrypoint-initdb.d
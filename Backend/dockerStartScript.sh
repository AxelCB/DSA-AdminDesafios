#!/bin/bash
/etc/init.d/postgresql start
sleep 15
if [ ! -e alreadyInitialized.lock ]
then
    echo "DB not initialized...run init"
    su postgres -c "psql postgres -tAc \"CREATE USER dsa WITH PASSWORD 'dsa'\" "
    su postgres -c "psql postgres -tAc \"CREATE DATABASE dsa\" "
    su postgres -c "psql postgres -tAc \"GRANT ALL PRIVILEGES ON DATABASE dsa to dsa\" "
    su postgres -c "psql postgres -d dsa -f dump.sql"
    echo "1" > alreadyInitialized.lock
else
    echo "DB already initialied"
fi

service apache2 start

cd /py

python server.py &

cd /

java -Djava.security.egd=file:/dev/./urandom -jar /app.jar
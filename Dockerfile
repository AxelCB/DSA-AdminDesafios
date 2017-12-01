FROM ubuntu:trusty

RUN echo debconf shared/accepted-oracle-license-v1-1 select true | debconf-set-selections
RUN echo debconf shared/accepted-oracle-license-v1-1 seen true | debconf-set-selections

RUN apt-get update && \
    apt-get install -y --no-install-recommends software-properties-common && \
    add-apt-repository -y ppa:webupd8team/java && \
    apt-get update && \
    apt-get install -y --no-install-recommends oracle-java8-installer oracle-java8-set-default

# Add the PostgreSQL PGP key to verify their Debian packages.
# It should be the same key as https://www.postgresql.org/media/keys/ACCC4CF8.asc
RUN apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys B97B0AFCAA1A47F044F244A07FCC7D46ACCC4CF8

# Add PostgreSQL's repository. It contains the most recent stable release
#     of PostgreSQL, ``9.5``.
RUN echo "deb http://apt.postgresql.org/pub/repos/apt/ precise-pgdg main" > /etc/apt/sources.list.d/pgdg.list

RUN apt-get update && apt-get install -y python-software-properties software-properties-common postgresql-9.5 postgresql-client-9.5 postgresql-contrib-9.5
RUN adduser dsa

USER postgres

# Create a PostgreSQL role named ``docker`` with ``docker`` as the password and
# then create a database `docker` owned by the ``docker`` role.
# Note: here we use ``&&\`` to run commands one after the other - the ``\``
#       allows the RUN command to span multiple lines.
RUN /etc/init.d/postgresql start

# Adjust PostgreSQL configuration so that remote connections to the
# database are possible.
RUN echo "host all  all    0.0.0.0/0  md5" >> /etc/postgresql/9.5/main/pg_hba.conf

# And add ``listen_addresses`` to ``/etc/postgresql/9.5/main/postgresql.conf``
RUN echo "listen_addresses='*'" >> /etc/postgresql/9.5/main/postgresql.conf

USER root

#Apache config

RUN apt-get install -y apache2

ADD FrontendWeb/dist /var/www/html/

RUN sed -i.bak 's#80#4200#g' /etc/apache2/sites-enabled/000-default.conf
RUN sed -i.bak 's#80#4200#g' /etc/apache2/sites-available/000-default.conf
RUN sed -i.bak 's#80#4200#g' /etc/apache2/ports.conf
RUN rm /etc/apache2/sites-available/000-default.conf.bak && rm /etc/apache2/sites-enabled/000-default.conf.bak && rm /etc/apache2/ports.conf.bak

RUN chmod -R 777 /var/www/html && service apache2 start

# End

# Python server config
RUN add-apt-repository ppa:fkrull/deadsnakes && \
    apt update && apt install -y python2.7 python-pip python-dev build-essential

RUN pip install requests

# Expose the PostgreSQL port
EXPOSE 5432
EXPOSE 8080
EXPOSE 4200

VOLUME /tmp
VOLUME /py
#ARG JAR_FILE
ADD mocks/server.py /py/server.py
ADD mocks/listado_de_equipos_y_usuarios /py/listado_de_equipos_y_usuarios
ADD Backend/dump.sql dump.sql
ADD Backend/build/libs/admindesafiosspringboot-0.0.1-SNAPSHOT.jar app.jar
ADD Backend/dockerStartScript.sh dockerStartScript.sh
RUN chmod 777 dockerStartScript.sh
ENTRYPOINT ["/dockerStartScript.sh"]
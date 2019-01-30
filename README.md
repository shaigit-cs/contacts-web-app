# Contacts Web App

##### Instructions to setup MySQL data base in Docker for the application to run locally:

``` shell
$ docker run --detach --name=mysql-docker --env="MYSQL_ROOT_PASSWORD=pass123" --env="TZ=Europe/Warsaw" --publish 6603:3306 mysql
$ docker exec -it mysql-docker bash

# mysql -u root -p

mysql> create database contacts_db;
```
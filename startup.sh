#!/bin/bash
#git clone https://github.com/VitorCardoso/favorite-songs
#cd /favorite-songs
sh install_docker.sh
sh install_jdk_maven.sh
mvn clean install -f ./users
mvn clean install -f ./songs
docker-compose up -d nginx
docker-compose logs -f
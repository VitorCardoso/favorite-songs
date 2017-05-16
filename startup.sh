#!/bin/bash
mvn clean install -f ./front-end
mvn clean install -f ./users
mvn clean install -f ./songs
docker-compose up -d
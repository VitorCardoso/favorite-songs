#!/bin/bash
mvn clean install -f ./users
mvn clean install -f ./songs
docker-compose up -d nginx
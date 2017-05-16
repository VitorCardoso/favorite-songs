# favorite-songs
Webapp - users favourite songs list DEMO

### Tecnologies
Java, Maven, Spring Boot, Mongo DB, Docker(docker-compose), AngularJS(v1)

### StartUp
#### Required to run:
- Maven
- Docker
- Docker Compose
#### Auto:
```
sh start.sh
```
#### Manual:
##### In root dir:
```
mvn clean install -f ./front-end/pom.xml
mvn clean install -f ./users/pom.xml
mvn clean install -f ./songs/pom.xml
docker-compose up -d
```
##### Check with:
```
docker-compose ps
```
### Webapp URL: 
- http://[ip-docker-machine]:80/songs

### API REST
#### Songs:
##### URL
- http://[ip-docker-machine]:92/api
```
GET     /songs              - Get all songs
GET     /songs/{uuid}       - Get a song
POST    /songs              - Create a song
PUT     /songs/{uuid}       - Update a song
DELETE  /songs/{uuid}       - Remove a song
```
#### Users:
##### URL
- http://[ip-docker-machine]:91/api
```
GET     /users              - Get all users
GET     /users/{uuid}       - Get a user
POST    /users              - Create a user
PUT     /users/{uuid}       - Update a user
DELETE  /users/{uuid}       - Remove a user

GET     /users/{uuid}/songs         - Get all user favorite songs
POST    /users/{uuid}/songs         - Add a song to user favorite songs list
DELETE  /users/{uuid}/songs/{id}    - Remove a song from user favorite songs list
```
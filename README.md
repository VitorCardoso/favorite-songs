# favorite-songs
Webapp - users favourite songs list DEMO

### Tecnologies
Java, Maven, Spring Boot, Mongo DB, Docker(docker-compose), AngularJS(v1)

### StartUp
#### Auto:
```
sh start.sh
```
#### Manual:
In root dir:

```
mvn clean install front-end
mvn clean install users
mvn clean install songs
docker-compose up -d
```
Check with:
```
docker-compose ps
```
### URL: 
http://[ip-docker-machine]:80/songs
http://....

### API REST

#### Songs:
- [x] GET     /songs              - Get all songs
- [x] GET     /songs/{uuid}       - Get a song
- [x] POST    /songs              - Create a song
- [x] PUT     /songs/{uuid}       - Update a song
- [x] DELETE  /songs/{uuid}       - Remove a song

#### Users:
- [x] GET     /users              - Get all users
- [x] GET     /users/{uuid}       - Get a user
- [x] POST    /users              - Create a user
- [x] PUT     /users/{uuid}       - Update a user
- [x] DELETE  /users/{uuid}       - Remove a user

- [x] GET     /users/{uuid}/songs         - Get all user favorite songs
- [x] POST    /users/{uuid}/songs         - Add a song to user favorite songs list
- [x] DELETE  /users/{uuid}/songs/{id}    - Remove a song from user favorite songs list

# favorite-songs
Webapp - users favourite songs list DEMO

TECNOLOGIES
Java, Maven, Spring Boot, Mongo DB, Docker(docker-compose), AngularJS(v1)

StartUp
Auto:
    sh start.sh
Manual:
    In root dir:
        mvn clean install front-end
        mvn clean install users
        mvn clean install songs
        docker-compose up -d
    Check with:
        docker-compose ps

URL: 
    http://[ip-docker-machine]:80/songs
Public URL:
    http://....

API REST

Songs:
GET     /songs              - Get all songs
GET     /songs/{uuid}       - Get a song
POST    /songs              - Create a song
PUT     /songs/{uuid}       - Update a song
DELETE  /songs/{uuid}       - Remove a song

Users:
GET     /users              - Get all users
GET     /users/{uuid}       - Get a user
POST    /users              - Create a user
PUT     /users/{uuid}       - Update a user
DELETE  /users/{uuid}       - Remove a user

GET     /users/{uuid}/songs         - Get all user favorite songs
POST    /users/{uuid}/songs         - Add a song to user favorite songs list
DELETE  /users/{uuid}/songs/{id}    - Remove a song from user favorite songs list

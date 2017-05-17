(function () {
    var app = angular.module('songs', ['ngRoute', 'ngSanitize', 'ui.bootstrap', 'songs-factories']);

    app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push('httpInterceptor');
        $httpProvider.interceptors.push('HttpRequestTimeoutInterceptor');
    }]);

    app.config(function ($routeProvider) {
        $routeProvider
            .when("/users", {
                templateUrl: "templates/users.html",
                controller: "UserController"
            })
            .when("/users/:id/songs", {
                templateUrl: "templates/favSongs.html",
                controller: "FavSongController"
            })
            .when("/songs", {
                templateUrl: "templates/songs.html",
                controller: "SongController"
            })
            .otherwise({
                redirectTo: "/users"
            });
    });

    app.controller('UserController', ['$http', '$scope', '$location', 'HttpPendingRequestsService', 'UsersService', function ($http, $scope, $location, HttpPendingRequestsService, UsersService) {
        $scope.cancelRequests = function () {
            HttpPendingRequestsService.cancelAll();
        };

        // init vars
        $scope.users = [];

        // get users
        $scope.query = function () {
            UsersService.query({}, function (data) {
                $scope.users = data;
            }, function (errorData) {
                console.error(errorData);
            });
        };

        //first time query
        $scope.query();

        // for form validation
        $scope.isValid = function () {
            return ($scope.name && $scope.email);
        };

        // to clean data
        $scope.cleanData = function () {
            $scope.name = $scope.email = null;
        };

        // to insert new user
        $scope.insert = function () {
            console.log("Insert User: " + $scope.name);
            var user = {
                "name": $scope.name,
                "email": $scope.email
            };
            UsersService.save(user, function (data) {
                $scope.users.push(data);
                $scope.cleanData();
            }, function (errorData) {
                console.error(errorData);
            });
        };

        // to remove a song
        $scope.removeUser = function (uuid) {
            console.log("remove User: " + uuid);
            UsersService.delete({
                id: uuid
            }, function (data) {
                $scope.query();
            }, function (errorData) {
                console.error(errorData);
            });
        };

        // navigation
        $scope.goToFavSongs = function (id) {
            $location.path('/users/' + id + '/songs');
        }
        $scope.goToSongs = function () {
            $location.path('/songs');
        };
        $scope.goToUsers = function () {
            $location.path('/users');
        };
    }]);

    app.controller('SongController', ['$http', '$scope', '$location', 'HttpPendingRequestsService', 'SongsService', function ($http, $scope, $location, HttpPendingRequestsService, SongsService) {
        // init vars
        $scope.songs = [];

        // get songs
        $scope.query = function () {
            SongsService.query({}, function (data) {
                $scope.songs = data;
            }, function (errorData) {
                console.error(errorData);
            });
        };

        //first time query
        $scope.query();

        // for form validation
        $scope.isValid = function () {
            return ($scope.title && $scope.artist && $scope.album);
        };

        // to clean data
        $scope.cleanData = function () {
            $scope.title = $scope.artist = $scope.album = null;
        };

        // to insert new song
        $scope.insert = function () {
            console.log("Insert Song: " + $scope.title);
            var song = {
                "title": $scope.title,
                "artist": $scope.artist,
                "album": $scope.album
            };
            SongsService.save(song, function (data) {
                $scope.songs.push(data);
                $scope.cleanData();
            }, function (errorData) {
                console.error(errorData);
            });
        };

        // to remove a song
        $scope.removeSong = function (uuid) {
            console.log("remove song: " + uuid);
            SongsService.delete({
                id: uuid
            }, function (data) {
                $scope.query();
            }, function (errorData) {
                console.error(errorData);
            });
        };
    }]);

    app.controller('FavSongController', ['$http', '$scope', '$location', '$routeParams', 'HttpPendingRequestsService', 'UsersService', function ($http, $scope, $location, $routeParams, HttpPendingRequestsService, UsersService) {
        $scope.params = $routeParams;

        //init var
        $scope.user = {};
        $scope.favSongs = [];
        $scope.selectedSong = undefined;

        // get user info
        $scope.getUserInfo = function () {
            UsersService.get({
                id: $scope.params.id
            }, function (data) {
                $scope.user = data;
            }, function (errorData) {
                console.error(errorData);
            });
        };

        // get user
        $scope.getUserInfo();

        // remove a fav song
        $scope.removeFav = function (userfavSongId) {
            console.log("remove fav song: " + userfavSongId);
        };

        // search songs by fields
        $scope.getSongs = function (keyword) {
            return $scope.favSongs;
        };

        // for form validation
        $scope.isValid = function () {
            return ($scope.selectedSong && $scope.selectedSong.uuid);
        };

        $scope.updateFields = function (song) {

        };

        // to insert new song
        $scope.insert = function () {
            console.log("Insert Fav Song: " + $scope.selectedSong.uuid + " for user " + $scope.user.name);
        };

        $scope.favSongs = [{
            "uuid": "123",
            "title": "ola",
            "artist": "xxxxxxx",
            "album": "oleole"
        }];
    }]);

})();
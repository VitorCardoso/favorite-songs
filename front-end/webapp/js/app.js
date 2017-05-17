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
                console.info(errorData);
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
                console.info(errorData);
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
                console.info(errorData);
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
                console.info(errorData);
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
                console.info(errorData);
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
                console.info(errorData);
            });
        };
    }]);

    app.controller('FavSongController', ['$http', '$scope', '$location', '$routeParams', 'HttpPendingRequestsService', 'UsersService', 'SongsService', 'FavSongsService', function ($http, $scope, $location, $routeParams, HttpPendingRequestsService, UsersService, SongsService, FavSongsService) {
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
                console.info(errorData);
            });
        };

        // get fav song by user
        $scope.getFavSongsByUser = function () {
            $scope.favSongs = [];
            FavSongsService.query({
                userId: $scope.params.id
            }, function (data) {
                data.forEach(function (userSong) {
                    SongsService.get({
                        id: userSong.songId
                    }, function (song) {
                        $scope.favSongs.push(song);
                    }, function (errorData) {
                        console.info(errorData);
                    });
                });
            }, function (errorData) {
                console.info(errorData);
            });
        };

        // get user
        $scope.getUserInfo();
        $scope.getFavSongsByUser();

        // remove a fav song
        $scope.removeFav = function (userfavSongId) {
            console.log("remove fav song: " + userfavSongId);
            FavSongsService.delete({
                userId: $scope.user.uuid,
                songId: userfavSongId
            }, function (data) {
                $scope.getFavSongsByUser();
            }, function (errorData) {
                console.info(errorData);
            });
        };

        // search songs by fields
        $scope.getSongs = function (keyword) {
            var url = window.location;
            return $http.get('http://' + url.hostname + '/api/songs/search/' + keyword).then(function (response) {
                var data = response.data;
                data.forEach(function (song, index, obj) {
                    if ($scope.favSongs.indexOf(song) > -1) {
                        // console.log(song);
                        // console.log(index);
                        // console.log(obj);
                        data.splice(index, 1);
                    }
                });
                return data;
            });
        };

        // for form validation
        $scope.isValid = function () {
            return ($scope.selectedSong && $scope.selectedSong.uuid);
        };

        // to insert new fav song
        $scope.insert = function () {
            console.log("Insert Fav Song: " + $scope.selectedSong.title + " for user " + $scope.user.name);
            var favSong = {
                userId: $scope.user.uuid,
                songId: $scope.selectedSong.uuid
            };
            FavSongsService.save(favSong, function (data) {
                $scope.selectedSong = undefined;
                $scope.getFavSongsByUser();
            }, function (errorData) {
                console.info(errorData);
            });
        };

    }]);

})();
(function () {
    var app = angular.module('songs', ['ngRoute', 'ui.bootstrap', 'songs-factories']);

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

    app.controller('UserController', ['$http', '$scope', '$location', 'HttpPendingRequestsService', function ($http, $scope, $location, HttpPendingRequestsService) {
        $scope.cancelRequests = function () {
            HttpPendingRequestsService.cancelAll();
        };
        $scope.users = [{
            "uuid": "123",
            "name": "ola",
            "email": "teste@teste.pt"
        }, {
            "uuid": "1232",
            "name": "ola",
            "email": "teste@teste.pt"
        }];

        $scope.isValid = function () {
            return ($scope.name && $scope.email);
        };

        $scope.insert = function () {
            console.log("Insert User: " + $scope.name);
        };

        $scope.removeUser = function (uuid) {
            console.log("remove User: " + uuid);
            return false;
        };

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

    app.controller('SongController', ['$http', '$scope', '$location', 'HttpPendingRequestsService', function ($http, $scope, $location, HttpPendingRequestsService) {
        $scope.songs = [{
            "uuid": "123",
            "title": "ola",
            "artist": "xxxxxxx",
            "album": "oleole"
        }, {
            "uuid": "123s",
            "title": "ola",
            "artist": "xxxxxxx",
            "album": "oleole"
        }];

        $scope.isValid = function () {
            return ($scope.title && $scope.artist && $scope.album);
        };

        $scope.insert = function () {
            console.log("Insert song: " + $scope.title);
        };

        $scope.removeSong = function (uuid) {
            console.log("remove song: " + uuid);
        };
    }]);

    app.controller('FavSongController', ['$http', '$scope', '$location', '$routeParams', 'HttpPendingRequestsService', function ($http, $scope, $location, $routeParams, HttpPendingRequestsService) {
        $scope.params = $routeParams;

        $scope.removeFav = function (userfavSongId) {
            console.log("remove fav song: " + userfavSongId);
        };

        $scope.favSongs = [{
            "uuid": "123",
            "title": "ola",
            "artist": "xxxxxxx",
            "album": "oleole"
        }];
    }]);

})();
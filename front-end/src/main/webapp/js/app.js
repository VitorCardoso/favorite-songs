(function () {
    var app = angular.module('songs', ['ngRoute', 'ui.bootstrap', 'songs-factories']);

    app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push('httpInterceptor');
        $httpProvider.interceptors.push('HttpRequestTimeoutInterceptor');
    }]);

    app.config(function ($routeProvider) {
        $routeProvider
            .when("/", {
                templateUrl: "templates/users.html",
                controller: "UserController"
            })
            .when("/add/user", {
                templateUrl: "templates/addUser.html",
                controller: "AddUserController"
            })
            .when("/add/song", {
                templateUrl: "templates/addSong.html",
                controller: "AddSongController"
            })
            .when("/songs", {
                templateUrl: "templates/songs.html",
                controller: "SongController"
            })
            .otherwise({
                redirectTo: "/"
            });
    });

    app.controller('UserController', ['$http', '$scope', '$location', 'HttpPendingRequestsService', function ($http, $scope, $location, HttpPendingRequestsService) {
        $scope.cancelRequests = function () {
            HttpPendingRequestsService.cancelAll();
        };
        $scope.users = [{
            "name": "ola",
            "email": "teste@teste.pt"
        }, {
            "name": "ola",
            "email": "teste@teste.pt"
        }];
        $scope.goToAddUser = function () {
            $location.path('/add/user');
        };
        $scope.goToSong = function () {
            $location.path('/song');
        };
    }]);
    app.controller('AddUserController', ['$http', '$scope', '$location', 'HttpPendingRequestsService', function ($http, $scope, $location, HttpPendingRequestsService) {
        $scope.goBack = function () {
            $location.path('/');
        };
    }]);
    app.controller('AddSongController', ['$http', '$scope', '$location', 'HttpPendingRequestsService', function ($http, $scope, $location, HttpPendingRequestsService) {
        $scope.goBack = function () {
            $location.path('/songs');
        };
    }]);
    app.controller('SongController', ['$http', '$scope', '$location', 'HttpPendingRequestsService', function ($http, $scope, $location, HttpPendingRequestsService) {
        $scope.goBack = function () {
            $location.path('/');
        };
        $scope.goToAddSong = function () {
            $location.path('/add/song');
        };
    }]);

})();
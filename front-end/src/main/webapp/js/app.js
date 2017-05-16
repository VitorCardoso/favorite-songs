(function() {
    var app = angular.module('songs', ['ngRoute', 'xeditable', 'ui.bootstrap', 'songs-factories', 'LocalStorageModule']);

    app.config(['$httpProvider', function($httpProvider) {
        $httpProvider.interceptors.push('httpInterceptor');
        $httpProvider.interceptors.push('HttpRequestTimeoutInterceptor');
    }]);

    app.controller('HomeController', ['$http', '$scope', 'HttpPendingRequestsService', function($http, $scope, HttpPendingRequestsService) {
        
    }]);

})();
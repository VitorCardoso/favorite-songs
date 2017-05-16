(function(){
  var app = angular.module('songs-factories', [ 'ngResource', 'ngSanitize']);

  app.factory('httpInterceptor', ['$q', '$rootScope', function($q, $rootScope) {
    var numLoadings = 0;
    return {
      request: function(config) {
        numLoadings++;
        $rootScope.$broadcast("loader_show");
        return config || $q.when(config);
      },
      response: function(response) {
        if ((--numLoadings) === 0) {
          $rootScope.$broadcast("loader_hide");
        }
        return response || $q.when(response);
      },
      responseError: function(response) {
        if (!(--numLoadings)) {
          $rootScope.$broadcast("loader_hide");
        }
        return $q.reject(response);
      }
    };
  }]);

  app.directive("loader", ['$rootScope', function($rootScope) {
    return function($scope, element, attrs) {
      $scope.$on("loader_show", function() {
        $scope.showEl = true;
      });
      return $scope.$on("loader_hide", function() {
        $scope.showEl = false;
      });
    };
  }]);
  
  app.service('HttpPendingRequestsService',  ['$q', function ($q) {
    var cancelPromises = [];
 
    function newTimeout() {
      var cancelPromise = $q.defer();
      cancelPromises.push(cancelPromise);
      return cancelPromise.promise;
    }
 
    function cancelAll() {
      angular.forEach(cancelPromises, function (cancelPromise) {
        cancelPromise.promise.isGloballyCancelled = true;
        cancelPromise.resolve();
      });
      cancelPromises.length = 0;
    }
 
    return {
      newTimeout: newTimeout,
      cancelAll: cancelAll
    };
  }]);
  
  app.factory('HttpRequestTimeoutInterceptor',  ['$q','HttpPendingRequestsService', function ($q, HttpPendingRequestsService) {
    return {
      request: function (config) {
        config = config || {};
        if (config.timeout === undefined && !config.noCancelOnRouteChange) {
          config.timeout = HttpPendingRequestsService.newTimeout();
        }
        return config;
      }
    };
  }]);
  
})();
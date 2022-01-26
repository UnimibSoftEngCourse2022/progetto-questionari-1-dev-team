'use strict';

angular.module('UNIMIBModules').component('cookies', {
    templateUrl: 'cookies/cookies.template.html',
    controller: ['$location', '$routeParams', '$scope', '$http', '$cookies',
        function Cookies($location, $routeParams, $scope, $http, $cookies) {

            $scope.myCookieVal = $cookies.get('cookie');
            $scope.setCookie = function (val) {
                $cookies.put('cookie', val);
            }
        }
    ]
});
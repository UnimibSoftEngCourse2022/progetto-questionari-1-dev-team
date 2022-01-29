'use strict';

angular.module('UNIMIBModules').component('getUser', {
    templateUrl: 'get-user/get-user.template.html',
    controller: ['$location', '$routeParams', '$scope', '$http', 'cookieService',
        function getUserController($location, $routeParams, $scope, $http, cookieService) {

            $scope.name = "";
            $scope.surname = "";
            $scope.username = "";
            $scope.email = "";

            $scope.getUser = function () {

                $scope.handleUser = function (response) {

                    $scope.result = response.data;
                    $scope.name = $scope.result.name;
                    $scope.surname = $scope.result.surname;
                    $scope.username = $scope.result.username;
                    $scope.email = $scope.result.email;
                }

                $http.get("/api/getUser/" + cookieService.getCookie("userId")).then(function onFulFilled(response) {

                    console.log(response);
                    $scope.handleUser(response);

                }, function errorCallback(response) {
                    console.error(response);
                });
            }
        }
    ]
});
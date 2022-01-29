'use strict';

angular.module('UNIMIBModules').component('getUser', {
    templateUrl: 'get-user/get-user.template.html',
    controller: ['$location', '$routeParams', '$scope', '$http', 'cookieService', 'authService',
        function getUserController($location, $routeParams, $scope, $http, cookieService, authService) {

            $scope.name = "";
            $scope.surname = "";
            $scope.username = "";
            $scope.email = "";
            $scope.idUser;
            $scope.isLogged = false;

            $scope.load = function () {
                if (authService.isLoggedIn()) {
                    $scope.idUser = cookieService.getCookie();
                    $scope.isLogged = true;
                } else if (!authService.isLoggedIn() && cookieService.getCookie() !== undefined) {
                    $scope.idUser = cookieService.getCookie();
                    $scope.isLogged = true;
                    authService.setUser($scope.idUser);
                }

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

            $scope.logoutUser = function () {
                if (authService.isLoggedIn()) {
                    authService.setUser(undefined);
                    cookieService.removeCookie();
                    $scope.isLogged = false;
                    alert("You have just logged out!");
                }
            }
        }
    ]
});
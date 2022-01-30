'use strict';

angular.module('UNIMIBModules').component('getUser', {
    templateUrl: 'get-user/get-user.template.html',
    controller: ['$location', '$routeParams', '$scope', '$http', 'cookieService', 'authService',
        function getUserController($location, $routeParams, $scope, $http, cookieService, authService) {

            $scope.name = "";
            $scope.surname = "";
            $scope.username = "";
            $scope.email = "";
            $scope.emptyResult = true;
            $scope.result;
            $scope.idUser;
            $scope.isLogged = false;

            $scope.load = function () {
                if (authService.isLoggedIn()) {
                    $scope.idUser = cookieService.getCookie("userId");
                    $scope.isLogged = true;
                } else if (!authService.isLoggedIn() && cookieService.getCookie("userId") !== undefined) {
                    $scope.idUser = cookieService.getCookie("userId");
                    $scope.isLogged = true;
                    authService.setUser($scope.idUser);
                }

                $http.get("/api/getUser/" + cookieService.getCookie("userId")).then(function onFulFilled(response) {
                    $scope.handleUser(response);

                }, function errorCallback(response) {
                    console.error(response);
                });

                $http({
                    url: "/api/findSurveyByCreator", method: "GET",
                    params: { userId : cookieService.getCookie("userId") }
                }).then(function onFulFilled(response) {
                    $scope.handleSurveysCreated(response)

                }, function errorCallback(response) {
                    console.error(response);
                });
            }

            $scope.displayDialog = function (index) {
                $scope.modalManagerLogout(index);
            }

            $scope.logoutUser = function () {
                if (authService.isLoggedIn()) {
                    authService.setUser(undefined);
                    cookieService.removeCookie("userId");
                    $scope.isLogged = false;
                    $scope.modalManagerLogout(2);
                    $location.path("/home");
                }
            }

            $scope.modalManagerLogout = function (index) {
                if (index == 1) {
                    $scope.displayModalLogout = 'block';
                } else if (index == 2) {
                    $scope.displayModalLogout = 'none';
                }
            }

            $scope.handleUser = function (response) {

                $scope.result = response.data;
                $scope.name = $scope.result.name;
                $scope.surname = $scope.result.surname;
                $scope.username = $scope.result.username;
                $scope.email = $scope.result.email;
            }

            $scope.handleSurveysCreated = function(response) {
                if (response.data.length > 0) {
                    $scope.emptyResult = false;
                    $scope.result = response.data;
                } else {
                    $scope.emptyResult = true;
                    $scope.result = [];
                }
            }

            $scope.modifySurvey = function(idx) {
                $location.path('/modifySurvey/' + $scope.result[idx].id);
            }
        }
    ]
});
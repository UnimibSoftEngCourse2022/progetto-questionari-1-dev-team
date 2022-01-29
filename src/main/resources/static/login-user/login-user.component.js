'use strict';

angular.module('UNIMIBModules').component('loginUser', {
    templateUrl: 'login-user/login-user.template.html',
    controller: ['$location', '$routeParams', '$scope', '$http', 'authService', 'cookieService',
        function loginUserController($location, $routeParams, $scope, $http, authService, cookieService) {

            $scope.username = "";
            $scope.password = "";
            $scope.isLogged = false;

            $scope.load = function () {
                if (authService.isLoggedIn()) {
                    $scope.isLogged = true;
                } else if (!authService.isLoggedIn() && cookieService.getCookie("userId") !== undefined) {
                    $scope.isLogged = true;
                }
            }

            $scope.logoutUser = function () {
                if (authService.isLoggedIn()) {
                    authService.setUser(undefined);
                    cookieService.removeCookie("userId");
                    $scope.isLogged = false;
                    alert("You have just logged out!");
                }
            }

            $scope.loginUser = function () {

                let data = {

                    name: null,
                    surname: null,
                    email: null,
                    username: $scope.username,
                    password: $scope.password,
                    questionsCreatedDTO: null,
                    surveysCreatedDTO: null

                }

                $http.post("/api/logInUser", data).then(function onFulfilled(response) {

                    authService.setUser($scope.username);
                    $scope.modalManager(1);
                    $scope.cookiesHandler(response.data.idUser);

                }, function errorCallback(response) {
                    console.error(response);
                    $scope.modalManager(2);
                });
            }

            $scope.resetForm = function () {
                $scope.username = "";
                $scope.password = "";
                $scope.modalManager(-2);
            }

            $scope.cookiesHandler = function (idUser) {
                if (cookieService.getCookie("userId") == null) {
                    cookieService.setCookie("userId", idUser);
                }

                if ((cookieService.getCookie("userId") != null) && (cookieService.getCookie("userId") !== idUser)) {
                    cookieService.removeCookie("userId");
                    cookieService.setCookie("userId", idUser);
                }
            }

            $scope.modalManager = function (index) {
                if (index == 1) {
                    $scope.displayModal1 = 'block';
                } else if (index == 2) {
                    $scope.displayModal2 = 'block';
                } else if (index == -2) {
                    $scope.displayModal2 = 'none'
                }
            }

            $scope.changePage = function () {
                $location.path("/home");
            }
        }
    ]
});
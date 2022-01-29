'use strict';

angular.module('UNIMIBModules').component('loginUser', {
    templateUrl: 'login-user/login-user.template.html',
    controller: ['$location', '$routeParams', '$scope', '$http', 'authService', 'cookieService',
        function loginUserController($location, $routeParams, $scope, $http, authService, cookieService) {

            $scope.username = "";
            $scope.password = "";
            $scope.isLogged = false;

            $scope.load = function() {
                if (authService.isLoggedIn()) {
                    $scope.isLogged = true;
                } else if (!authService.isLoggedIn() && cookieService.getCookie() !== undefined) {
                    $scope.isLogged = true;
                }
            }

            $scope.logoutUser = function () {
                if (authService.isLoggedIn()) {
                    authService.setUser(undefined);
                    cookieService.removeCookie();
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

                    alert("Login Successful!");
                    authService.setUser($scope.username);
                    $scope.cookiesHandler(response.data.idUser);
                    $location.path("/home");

                }, function errorCallback(response) {

                    $scope.resetForm();
                    alert("Invalid username and password. Please try again.");

                });
            }

            $scope.resetForm = function () {
                $scope.username = "";
                $scope.password = "";
            }

            $scope.cookiesHandler = function (idUser) {
                if (cookieService.getCookie("userId") == null) {
                    cookieService.setCookie("userId", idUser);
                }

                if ((cookieService.getCookie() != null) && (cookieService.getCookie() !== idUser)) {
                    cookieService.removeCookie();
                    cookieService.setCookie(idUser);
                }
            }
        }
    ]
});
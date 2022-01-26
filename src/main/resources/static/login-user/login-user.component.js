'use strict';

angular.module('UNIMIBModules').component('loginUser', {
    templateUrl: 'login-user/login-user.template.html',
    controller: ['$location', '$routeParams', '$scope', '$http',
        function loginUserController($location, $routeParams, $scope, $http) {

            $scope.username = "";
            $scope.password = "";

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

                    console.log(response);
                    alert("Login Successful!");
                    $location.path("/home");

                }, function errorCallback(response) {

                    console.error(response);
                    alert("Invalid username and password. Please try again.");

                });
            }
        }
    ]
});
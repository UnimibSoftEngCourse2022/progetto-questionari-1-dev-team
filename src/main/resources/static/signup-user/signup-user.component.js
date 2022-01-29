'use strict';

angular.module('UNIMIBModules').component('signupUser', {
    templateUrl: 'signup-user/signup-user.template.html',
    controller: ['$location', '$routeParams', '$scope', '$http',
        function signupUserController($location, $routeParams, $scope, $http) {

            $scope.username = "";
            $scope.name = "";
            $scope.surname = "";
            $scope.email = "";
            $scope.password = "";

            $scope.signupUser = function () {

                let data = {

                    username: $scope.username,
                    name: $scope.name,
                    surname: $scope.surname,
                    email: $scope.email,
                    password: $scope.password,
                    questionsCreatedDTO: null,
                    surveysCreatedDTO: null

                }

                $http.post("/api/signUpUser", data).then(function onFulfilled(response) {

                    alert("User created! Proceed to login in order to navigate across the site.");
                    $location.path("/loginUser");

                }, function errorCallback(response) {

                    console.error(response);
                });
            }
        }
    ]
});
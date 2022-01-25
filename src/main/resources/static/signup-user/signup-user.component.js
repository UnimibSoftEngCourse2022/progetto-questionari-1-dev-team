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

                console.log(data);

                $http.post("/api/signUpUser", data).then(function onFulfilled(response) {

                    console.log(response);

                }, function errorCallback(response) {

                    console.error(response);
                });
            }
        }
    ]
});
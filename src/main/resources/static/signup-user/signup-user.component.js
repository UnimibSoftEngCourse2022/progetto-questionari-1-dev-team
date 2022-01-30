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

                    $scope.modalManager(1);

                }, function errorCallback(response) {

                    console.error(response);
                    $scope.modalManager(2);
                });
            }

            $scope.resetForm = function () {
                $scope.username = "";
                $scope.name = "";
                $scope.surname = "";
                $scope.email = "";
                $scope.password = "";
                $scope.modalManager(-2);
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
                $location.path("/loginUser");
            }
        }
    ]
});
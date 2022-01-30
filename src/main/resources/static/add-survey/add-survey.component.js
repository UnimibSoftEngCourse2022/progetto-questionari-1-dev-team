'use strict';

angular.
	module('UNIMIBModules').
	component('addSurvey', {
		templateUrl: 'add-survey/add-survey.template.html',
		controller: ['$location', '$scope', '$http', 'cookieService', 'authService',
			function addSurveyController($location, $scope, $http, cookieService, authService) {

				$scope.idUser;
				$scope.name = "";
				$scope.showMessageErr = false;
				$scope.showMessageConf = false;
				$scope.message = "";

				$scope.load = function () {
					if (authService.isLoggedIn()) {
						$scope.idUser = cookieService.getCookie("userId");
						$scope.isLogged = true;
					} else if (!authService.isLoggedIn() && cookieService.getCookie("userId") !== undefined) {
						$scope.idUser = cookieService.getCookie("userId");
						$scope.isLogged = true;

					}
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

				//error alert
				$scope.showAlert = function(text) {
					alert('ERROR - ' + text)
				}

				$scope.addSurvey = function() {
					if ($scope.name !== undefined && $scope.name != "" && $scope.name.replace(/\s/g, '').length) {
						let data = {
							surveyName: $scope.name,
							creationDate: null,
							userDTO: {
								id: $scope.idUser,
							}
							, questionsDTO: null
						}

						$http.post("/api/addSurvey", data).then(function onfulFilled(response) {

							$scope.showMessageConf = true
							$scope.showMessageErr = false
							$scope.message = "Survey created."
						}, function errorCallback(response) {

							$scope.showMessageErr = true
							$scope.showMessageConf = false
							$scope.message = "Error - cannot create the survey."
						});

					} else {
						$scope.showMessageErr = true
						$scope.showMessageConf = false
						$scope.message = "Error - Please enter the Survey name."
					}
				}
			}]
	});
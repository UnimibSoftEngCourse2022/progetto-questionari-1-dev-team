'use strict';

angular.
	module('UNIMIBModules').
	component('home', {
		templateUrl: 'home/home.template.html',
		controller: ['$location', '$routeParams', '$scope', '$http', 'authService',
			function homeController($location, $routeParams, $scope, $http, authService) {
				$scope.idUser = 1 //from cookie
				$scope.isLogged = false
				$scope.isEmptyResult = true
				$scope.searchResult = []
				$scope.isCreator = []
				$scope.messageError = ""
				$scope.showMessageError = false
				$scope.compilationCode = ""

				//error alert 
				$scope.showAlert = function(text) {

					alert('ERROR - ' + text)
				}

				$scope.$watch(authService.isLoggedIn, function (value, oldValue) {

					if(!value && oldValue) {
						console.log("Disconnect");
						$location.path('/loginUser');
					}

					if(value) {
						console.log("Connect");
						$location.path('/home');
					}

				}, true);

				//Start-up function
				$scope.load = function() {

					if ($scope.idUser != null){
						$scope.isLogged = true;
					}	
					$http.get("/api/findAllSurveysNoQuestion").then(function onfulFilled(response) {
						$scope.handleSurveys(response)
					})
				}


				//compile by code for no-registered users
				$scope.compileByCode = function() {
					if ($scope.compilationCode !== undefined && $scope.compilationCode != "" && $scope.compilationCode.replace(/\s/g, '').length) {
						$location.path('/compileSurvey/')
					} else {
						$scope.messageError = "ERROR - This field cannot be empty."
						$scope.showMessageError = true 
					}
				}

				// find survey by text : its code or name
				$scope.findSurveyByText = function(searchText) {
					if (searchText !== undefined && searchText != "" && searchText.replace(/\s/g, '').length) {
						$http.get("/api/findSurveyByTextNoQuestion/?text=" + searchText).then(function onfulFilled(response) {
							$scope.handleSurveys(response)
						}, function errorCallback(response) {
							$scope.survey = {}
							$scope.isEmptyResult = true
						});
					} else {
						$scope.load()
					}
				}

				//redirect compile survey
				$scope.compileSurvey = function(idx) {
					$location.path('/compileSurvey/' +  $scope.result[idx].id)
				}

				//redirect modify survey
				$scope.modifySurvey = function(idx) {
					$location.path('/modifySurvey/' +$scope.result[idx].id)
				}
				
				//redirect new question
				$scope.newQuestion = function() {
					$location.path('/addQuestion')
				}
				
				//redirect new survey
				$scope.newSurvey = function() {
					$location.path('/addSurvey')
				}

				//Utility function for showing surveys and setting permissions
				$scope.handleSurveys = function(response) {
					console.log(response.data)
					if (response.data.length > 0) {
						$scope.isEmptyResult = false;
						$scope.result = response.data;
						$scope.result.forEach(function(survey, idx) {
							if (survey.userDTO.id == $scope.idUser) {
								$scope.isCreator[idx] = true
							} else {
								$scope.isCreator[idx] = false
							}
						});
					} else {
						$scope.isEmptyResult = true;
						$scope.result = [];
					}
				}
			}
		]
	});
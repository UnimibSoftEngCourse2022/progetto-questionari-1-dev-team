'use strict';

angular.
	module('UNIMIBModules').
	component('home', {
		templateUrl: 'home/home.template.html',
		controller: ['$location', '$routeParams', '$scope', '$http',
			function homeController($location, $routeParams, $scope, $http) {
				$scope.idUser;
				$scope.isLogged = false
				$scope.isEmptyResult = true
				$scope.searchResult = []
				$scope.isCreator = []
				$scope.messageError = ""
				$scope.showMessageError = false
				$scope.compilationCode = ""
				$scope.randomCode = -1

				//error alert 
				$scope.showAlert = function(text) {

					alert('ERROR - ' + text)
				}

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
					$scope.modalQuestion = idx;
					$scope.errorEmail = false;

					if($scope.idUser !== undefined)
					$location.path('/compileSurvey/' +  $scope.result[idx].id)
					else{
						$http.get("/api/getNewCode").then(function onfulFilled(response) {
							$scope.randomCode = response.data.compilationCode;
						});
						$scope.modalManager(idx);
					}
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

				$scope.modalManager = function(index) {
					if ($scope.displayModal === 'block') {
						$scope.displayModal = 'none';
						$scope.displaySearchButton = "block"
					} else {
						$scope.displayModal = 'block';
						$scope.displaySearchButton = "none"
					}

					if (index > -1) {
						$scope.modalQuestion = index
						$scope.editQuestion = $scope.searchQuestions[index].user.id === $scope.idUser;
					}
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

				$scope.compileSurveyGuest = function (idx){
					if($scope.emailGuest == null || $scope.emailGuest === "") {
						$scope.errorEmail = true;
					}else{
						let data = {
							username: $scope.randomCode,
							name: null,
							surname: null,
							email: $scope.emailGuest,
							password: "",
							questionsCreatedDTO: null,
							surveysCreatedDTO: null,
							compilationId: $scope.randomCode
						}

						$http.post("/api/signUpUser", data).then(function onFulfilled(response) {
							//settare cookie response.data.idUser
							console.log(response.data.idUser);
							$location.path('/compileSurvey/' +  $scope.result[idx].id)

						}, function errorCallback(response) {

							console.error(response);
						});
					}
				}
			}
		]
	});
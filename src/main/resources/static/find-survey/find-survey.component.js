'use strict';

angular.
	module('UNIMIBModules').
	component('findSurvey', {
		templateUrl: 'find-survey/find-survey.template.html',
		controller: ['$location' , '$routeParams', '$scope', '$http',
			function findSurveyController($location , $routeParams, $scope, $http) {
				//in routeParams mi aspetto di avere l'Id utente se è loggato
				$scope.idUserLogged = 1;
				$scope.isEmptyResult = true;
				$scope.surveyNameSearch = "";
				$scope.creatorNameSearch = "";
				$scope.result = [];
				$scope.isCreator = []
				$scope.surveyCodeCompile = 33
				
				
				$scope.surveyCodeCompileRedirect = function(){
					
					let survey_id = $scope.surveyCodeCompile
					$location.path('/compileSurvey/' + survey_id)
					
				}
				
				
				
				$scope.compileSurvey = function(idx){
					let surveyToCompile = $scope.result[idx];
					$location.path('/compileSurvey/' + surveyToCompile.id)
				}


				$scope.modifySurvey = function(idx){
					let surveyToModify = $scope.result[idx];
					$location.path('/modifySurvey/' + surveyToModify.id + '/' + $scope.idUserLogged)
				}


				$scope.handleSurveys = function(response) {
					if (response.data.length > 0) {
						$scope.isEmptyResult = false;
						$scope.result = response.data;
						$scope.result.forEach(function(survey, idx) {
							if (survey.userDTO.id == $scope.idUserLogged) {
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
				
				
				$scope.load = function() {

					$http.get("/api/findAllSurveys").then(function onfulFilled(response) {
						$scope.handleSurveys(response)

					}, function errorCallback(response) {
						$scope.survey = {}
					});

				}

				$scope.findSurveyByName = function() {
					if ($scope.surveyNameSearch != "" && $scope.surveyNameSearch.replace(/\s/g, '').length) {
						$http.get("/api/findSurveyByNameSubsequence/?name=" + $scope.surveyNameSearch).then(function onfulFilled(response) {
							$scope.handleSurveys(response)

						}, function errorCallback(response) {
							$scope.survey = {}
						});
					}

				}

				$scope.findSurveyByCreator = function() {
					if ($scope.creatorNameSearch != "" && $scope.creatorNameSearch.replace(/\s/g, '').length) {

						$http.get("/api/getSurveysCreated/?username=" + $scope.creatorNameSearch).then(function onfulFilled(response) {

							$scope.handleSurveys(response)

						}, function errorCallback(response) {
							$scope.survey = {}
						});
					}

				}

			}
		]
	});
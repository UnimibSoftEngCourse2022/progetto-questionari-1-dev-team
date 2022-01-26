'use strict';

angular.
	module('UNIMIBModules').
	component('addSurvey', {
		templateUrl: 'add-survey/add-survey.template.html',
		controller: ['$location', '$routeParams', '$scope', '$http',
			function addSurveyController($location, $routeParams, $scope, $http) {

				$scope.idUser = 1 //from cookie
				$scope.name = ""
				$scope.showMessageErr = false
				$scope.showMessageConf = false
				$scope.message = ""

				$scope.addSurvey = function() {

					if ($scope.name !== undefined && $scope.name != "" && $scope.name.replace(/\s/g, '').length) {
						let actualDate = new Date()
						let data = {
							surveyName: $scope.name,
							creationDate: actualDate.getFullYear() + '-' + (actualDate.getMonth() + 1) + '-' + actualDate.getDate() + " " + actualDate.getMinutes() + ":" + actualDate.getSeconds(),
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
						
					}else{
							$scope.showMessageErr = true
							$scope.showMessageConf = false
							$scope.message = "Error - Please enter the Survey name."
					}
				}
			}]
	});
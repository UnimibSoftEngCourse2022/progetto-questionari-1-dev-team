'use strict';

angular.
	module('UNIMIBModules').
	component('addSurvey', {
		templateUrl: 'add-survey/add-survey.template.html',
		controller: ['$location', '$routeParams', '$scope', '$http',
			function addSurveyController($location, $routeParams, $scope, $http) {

				$scope.name = "";

				$scope.addSurvey = function() {

					let actualDate = new Date()

					let data = {

						surveyName: $scope.name,
						creationDate: actualDate.getFullYear() + '-' + (actualDate.getMonth() + 1) + '-' + actualDate.getDate() + " " + actualDate.getMinutes() + ":" + actualDate.getSeconds(),
						userDTO: {
							id: $routeParams.idUser,
						},
						questionsDTO: null
					}

					$http.post("/api/addSurvey", data).then(function onfulFilled(response) {

						console.log(response.data.response);

					}, function errorCallback(response) {

						alert("Error");
						console.error(response);
					});



				}
			}]

	});
'use strict';

angular.
	module('UNIMIBModules').
	component('compileSurvey', {
		templateUrl: 'compile-survey/compile-survey.template.html',
		controller: ['$location' , '$routeParams', '$scope', '$http',
			function compileSurveyController($location , $routeParams, $scope, $http) {
				console.log("OKKK")
				$scope.surveyId = routeParams.idSurvey;
				$scope.userId = routeParams.idUser ;
			}
		]
	});
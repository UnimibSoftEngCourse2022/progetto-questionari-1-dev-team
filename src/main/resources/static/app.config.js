'use strict';

angular.
	module('UNIMIBModules').
	config(['$routeProvider',
		function config($routeProvider) {
			$routeProvider.
				when('/findSurvey', {
					template: '<find-survey></find-survey>',
				}).when('/modifySurvey/:idSurvey/:idUser', {
					template: '<modify-survey></modify-survey>'
				}).when('/addSurvey/:idUser', {
					template: '<add-survey></add-survey>'
				}).when('/compileSurvey/:idSurvey/:idUser', {
					template: '<compile-survey></compile-survey>'
				}).when("/loginUser", {
					template: '<login-user></login-user>'
				}).otherwise({ redirectTo: "/findSurvey" });
		}
	]);
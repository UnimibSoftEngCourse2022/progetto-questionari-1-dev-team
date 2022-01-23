'use strict';

angular.
	module('UNIMIBModules').
	config(['$routeProvider',
		function config($routeProvider) {
			$routeProvider.
				when('/findSurvey', {
					template: '<find-survey></find-survey>',
				}).
				when('/modifySurvey/:idSurvey/:idUser', {
					template: '<modify-survey></modify-survey>'
				}).
				when('/editQuestion/:questionId/:userId', {
					template: '<editQuestion></editQuestion>'
				}).
				when('/addSurvey/:idUser', {
					template: '<addSurvey></addSurvey>'
				}).
				when('/compileSurvey/:idSurvey/:idUser', {
					template: '<compileSurvey></compileSurvey>'
				}).
				otherwise('/findSurvey');
		}
	]);
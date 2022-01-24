'use strict';

angular.
	module('UNIMIBModules').
	config(['$routeProvider',
		function config($routeProvider) {
			$routeProvider.
				when('/home', {
					template: '<home></home>',
				}).when('/modifySurvey/:idSurvey', {
					template: '<modify-survey></modify-survey>'
				}).when('/addSurvey/:idUser', {
					template: '<addSurvey></addSurvey>'
				}).when('/compileSurvey/:idSurvey/', {
					template: '<compileSurvey></compileSurvey>'
				}).when("/addQuestion", {
                    template: '<add-question></add-question>'
                }).when("/editQuestion/:idQuestion", {
                    template: '<edit-question></edit-question>'
                }).otherwise({ redirectTo: "/home" });
		}
	]);

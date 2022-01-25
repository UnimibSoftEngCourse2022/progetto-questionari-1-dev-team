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
				}).when('/addSurvey', {
					template: '<add-survey></add-survey>'
				}).when('/compileSurvey/:idSurvey/', {
					template: '<compile-survey></compile-survey>'
				}).when("/addQuestion", {
                    template: '<add-question></add-question>'
                }).when("/editQuestion/:idQuestion", {
                    template: '<edit-question></edit-question>'
                }).otherwise({ redirectTo: "/home" });
		}
	]);

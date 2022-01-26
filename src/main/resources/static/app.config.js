'use strict';

angular.module('UNIMIBModules', ['ngRoute', 'ngCookies']).config(['$routeProvider',
    function config($routeProvider) {
        $routeProvider.when('/home', {
            template: '<home></home>',
        }).when('/modifySurvey/:idSurvey', {
            template: '<modify-survey></modify-survey>'
        }).when("/editQuestion/:idQuestion", {
			template: '<edit-question></edit-question>'
        }).when("/addQuestion", {
			template: '<add-question></add-question>'
		}).when('/addSurvey', {
            template: '<add-survey></add-survey>'
        }).when('/compileSurvey/:idSurvey', {
            template: '<compile-survey></compile-survey>'
        }).when("/editSurveyAnswer/:surveyId", {
			template: "<edit-survey-answer></edit-survey-answer>"
        }).when("/loginUser", {
            template: '<login-user></login-user>'
        }).when("/signupUser", {
            template: '<signup-user></signup-user>'
        }).when("/getUser/:idUser", {
            template: '<get-user></get-user>'
        }).when("/cookies", {
            template: '<cookies></cookies>'
        }).otherwise({redirectTo: "/home"});
    }
]);
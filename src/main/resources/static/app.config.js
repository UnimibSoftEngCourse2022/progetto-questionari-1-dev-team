'use strict';

app.config(['$routeProvider', function config($routeProvider) {

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
		}).when("/compileSurvey/:surveyId", {
			template: '<compile-survey></compile-survey>'
		}).when("/editSurveyAnswer/:surveyId", {
			template: "<edit-survey-answer></edit-survey-answer>"
		}).otherwise({ redirectTo: "/findSurvey" });
}]);
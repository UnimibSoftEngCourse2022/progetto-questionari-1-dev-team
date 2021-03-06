'use strict';

window.routes =
    {
        '/home': {
            template: '<home></home>',
            requireLogin: false
        },

        '/modifySurvey/:idSurvey': {
            template: '<modify-survey></modify-survey>',
            requireLogin: true
        },

        '/editQuestion/:idQuestion': {
            template: '<edit-question></edit-question>',
            requireLogin: true
        },

        '/addQuestion': {
            template: '<add-question></add-question>',
            requireLogin: true
        },

        '/addSurvey': {
            template: '<add-survey></add-survey>',
            requireLogin: true
        },

        '/compileSurvey/:surveyId': {
            template: '<compile-survey></compile-survey>',
            requireLogin: true
        },

        '/compileSurvey/:surveyId/:userId': {
            template: '<compile-survey></compile-survey>',
            requireLogin: false
        },

        '/loginUser': {
            template: '<login-user></login-user>',
            requireLogin: false
        },

        '/signupUser': {
            template: '<signup-user></signup-user>',
            requireLogin: false
        },

        '/getUser': {
            template: '<get-user></get-user>',
            requireLogin: true
        }
    };

app.config(function config($routeProvider) {

    for (let path in window.routes) {
        $routeProvider.when(path, window.routes[path])
    }

    $routeProvider.otherwise({redirectTo: "/home"})

}).run(['$rootScope', '$location', 'cookieService', 'authService', function ($rootScope, $location, cookieService, authService) {
    $rootScope.$on('$locationChangeStart', function (event, next, current) {

        for (let i in window.routes) {
            if (next.indexOf(i) !== -1) {
                if (!authService.isLoggedIn() && (cookieService.getCookie("userId") === undefined) && window.routes[i].requireLogin) {
                    event.preventDefault();
                    $location.path('/loginUser');
                }
            }
        }
    });
}]);
const app = angular.module("UNIMIBModules", ["ngRoute"])
    .config(function($routeProvider) {

        $routeProvider.when("/", {
            templateUrl: "/template/home.html",
            controller: "homeCtrl"
        }).when("/add_answer", {
            templateUrl: "/template/add_answer.html",
            controller: "addAnswerCtrl"
        }).when("/edit_answer", {
            templateUrl: "/template/edit_answer.html",
            controller: "editAnswerCtrl"
        }).otherwise({ redirectTo: "/" });
    });

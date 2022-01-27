'use strict';

angular.
module('UNIMIBModules').
component('editQuestion', {
    templateUrl: 'edit-question/edit-question.template.html',
    controller: ['$location', '$routeParams', '$scope', '$http',
        function editQuestionController($location, $routeParams, $scope, $http) {
            let v;
            let start;
            let counter;
            let tmpObj = {};
            let qType;
            let check;

            $http.get("http://localhost:5000/api/getQuestion/" + $routeParams.idQuestion)
                .then(function(response) {
                    $scope.question =  response.data;
                    $scope.questiontext = $scope.question.text;

                    if($scope.question.questionType === "OPEN") {
                        start=true;
                        counter = 0;
                    }else{
                        start = false;
                        $scope.questionAnswer = $scope.question.closeEndedAnswerDTOSet;
                        counter = $scope.question.closeEndedAnswerDTOSet.length;
                    }
                });

            $http.get("http://localhost:5000/api/findCategories")
                .then(function(response) {
                    $scope.categories =  response.data;
                });

            $scope.showQuestionType = function (value) {
                v = value;

                if(value === 0) {
                    $scope.errorAnswer = false;
                    qType = "OPEN";
                    $scope.showHideClosedM = false;
                }

                if(value === 1) {
                    $scope.errorAnswer = false;
                    if(start)
                        $scope.questionAnswer = [];
                    start = false;
                    qType = "MULTIPLECLOSED";
                    $scope.showHideClosedM = true;
                }

                if(value === 2) {
                    $scope.errorAnswer = false;
                    if(start)
                        $scope.questionAnswer = [];
                    start = false;
                    qType = "SINGLECLOSED";
                    $scope.showHideClosedM = true;
                }
            };

            $scope.newAnswer = function ($event) {
                counter++;
                $scope.questionAnswer.push({text : ''});
                $event.preventDefault();
            };

            $scope.removeAnswer = function ($index) {
                var deletedQuestion = $scope.questionAnswer.splice($index, 1)[0];

                if(deletedQuestion.id !== null && !angular.isUndefined(deletedQuestion.id)) {
                    $http.delete("http://localhost:5000/api/deleteCloseEndedAnswer/"+deletedQuestion.id)
                        .then(function(response) {
                            $scope.prova = "http://localhost:5000/api/deleteCloseEndedAnswer/"+deletedQuestion.id;
                        });
                }

                counter--;
            };

            $scope.editQuestion = function() {
                $scope.errorQuestion = false;
                $scope.errorCategory = false;
                $scope.errorQuestionType = false;
                $scope.errorAnswer = false;
                check=true;

                if($scope.questiontext == null || $scope.questiontext === "") {
                    $scope.errorQuestion = true;
                    check = false;
                }

                if($scope.categorySelected == null){
                    $scope.errorCategory = true;
                    check = false;
                }

                if(v == null){
                    $scope.errorQuestionType = true;
                    check = false;
                }

                if($scope.questionAnswer != null && v!== 0)
                    angular.forEach($scope.questionAnswer, function (question, key) {
                        if(counter < 2 || question.text == null || question.text === ""){
                            check = false;
                            $scope.errorAnswer = true;
                        }
                    });

                if(v !== 0 && counter === 0){
                    check = false;
                    $scope.errorAnswer = true;
                }

                if(check) {
                    tmpObj["id"] = $scope.question.id;
                    tmpObj["user"] = $scope.question.user;
                    tmpObj["category"] = angular.fromJson($scope.categorySelected);
                    tmpObj["text"] = $scope.questiontext;
                    tmpObj["questionType"] = qType;

                    $http.patch("http://localhost:5000/api/modifyQuestion", tmpObj, 'application/json; charset=utf-8')
                        .then(function (response) {
                            $scope.prova = "ok";
                        });

                    if(v!==0){
                        angular.forEach($scope.questionAnswer, function (answer, key) {

                            answer["questionDTO"] = {"id": $scope.question.id};

                            if(answer.id !== null && !angular.isUndefined(answer.id)){
                                $http.patch("http://localhost:5000/api/modifyCloseEndedAnswer", answer, 'application/json; charset=utf-8')
                                    .then(function (response) {
                                        $scope.prova = "Domanda modificata con successo!";
                                    });
                            }else {
                                $http.post("http://localhost:5000/api/addCloseEndedAnswer", answer, 'application/json; charset=utf-8')
                                    .then(function (response) {
                                        $scope.prova = "Domanda modificata con successo!";
                                    });
                            }
                        });
                    }else{
                        angular.forEach($scope.questionAnswer, function (question, key) {
                            if(question.id !== null && !angular.isUndefined(question.id))
                                $http.delete("http://localhost:5000/api/deleteCloseEndedAnswer/"+question.id)
                                    .then(function (response) {
                                        $scope.prova = "Domanda inserita con successo!";

                                    });
                        });
                    }
                }


            };

            $scope.removeQuestion = function(){
                $http.delete("http://localhost:5000/api/deleteQuestion/" + $scope.question.id)
                    .then(function (response) {
                        $scope.prova = "Domanda eliminata con successo!";
                    });

                angular.forEach($scope.questionAnswer, function (answer, key) {
                    if(answer.id !== null && !angular.isUndefined(answer.id))
                        $http.delete("http://localhost:5000/api/deleteCloseEndedAnswer/"+answer.id)
                            .then(function (response) {
                                $scope.prova = "Risposta eliminata con successo!";
                            });
                });
                $location.path('/home');
            };
        }]
});
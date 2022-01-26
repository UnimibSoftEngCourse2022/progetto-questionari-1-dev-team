'use strict';

app.component('editSurveyAnswer', {
    templateUrl: 'edit-survey-answer/edit-survey-answer.template.html',
    controller: function($scope, $http, $routeParams, $uibModal) {

        $scope.model = {};
        $scope.options = [];
        $scope.survey = {};
        $scope.questions = [];
        $scope.answers = [];
        $scope.currentAnswer = {};
        $scope.questionIndex = 0;

        $scope.load = function() {

            $http.get("/api/findSurvey?id=" + $routeParams.surveyId).then(function onFulfilled(response) {

                $scope.survey = response.data;
            }, function errorCallback(response) {

                if (response.status === 404) {
                    alert("Survey not found.");
                } else {
                    alert("Error");
                    console.error(response);
                }
            });

            $http.get("/api/findQuestionForSurvey/" + $routeParams.surveyId).then(function onFulfilled(response) {

                $scope.questions = response.data;

                $http.get("/api/findSurveyAnswersForUser/?surveyId=" + $routeParams.surveyId + "&userId=1")
                    .then(function onFulfilled(response) {

                        $scope.answers = response.data;
                        $scope.selectQuestion();
                    }, function errorCallback(response) {

                        if (response.status === 404) {
                            alert("You have not compiled this survey yet.");
                        } else {
                            alert("Error");
                            console.error(response);
                        }
                    });
            }, function errorCallback(response) {

                if (response.status === 404) {
                    alert("No questions found.");
                } else {
                    alert("Error");
                    console.error(response);
                }
            });
        }

        $scope.selectQuestion = function() {

            if ($scope.questionIndex < $scope.questions.length) {
                for (let answer of $scope.answers) {
                    if (answer.questionDTO.id === $scope.questions[$scope.questionIndex].id) {
                        $scope.currentAnswer = answer;
                        if ($scope.questions[$scope.questionIndex].questionType === "SINGLECLOSED")
                            $scope.model.closeended_answer = answer.closeEndedAnswerDTOs[0].id;
                        else if ($scope.questions[$scope.questionIndex].questionType === "MULTIPLECLOSED") {
                            for (let closeEndedAnswer in $scope.questions[$scope.questionIndex].closeEndedAnswerDTOSet)
                                if (answer.closeEndedAnswerDTOs
                                    .find(v => v.id === $scope.questions[$scope.questionIndex].closeEndedAnswerDTOSet[closeEndedAnswer].id))
                                    $scope.options[closeEndedAnswer] = true;
                        }
                        break;
                    }
                }
            }
        }

        $scope.skipQuestion = function() {

            if ($scope.questionIndex !== $scope.questions.length - 1) {
                $scope.questionIndex += 1;
                $scope.selectQuestion();
            } else {
                let modal = $uibModal.open({
                    animation: true,
                    windowClass: "show",
                    templateUrl: "template/close-survey.template.html",
                    controller: function($scope, $http, $location) {

                        $scope.submit = function() {

                            $http.post("/api/saveModifiedSurveyAnswers?surveyId=" + $routeParams.surveyId + "&userId=1")
                                .then(function onFulfilled() {

                                    modal.close();
                                    $location.path("/");
                                }, function errorCallback(response) {

                                    modal.close();
                                    alert("Error");
                                    console.error(response);
                                });
                        }

                        $scope.cancel = function() {

                            modal.close();
                        }
                    }
                });
            }
        }

        $scope.submit = function() {

            let data = {
                id: $scope.currentAnswer.id,
                answerText: null,
                closeEndedAnswerDTOs: null,
                userDTO: {
                    id: 1
                },
                surveyDTO: {
                    id: $routeParams.surveyId
                },
                questionDTO: {
                    id: $scope.questions[$scope.questionIndex].id
                }
            }

            switch ($scope.questions[$scope.questionIndex].questionType) {
                case "OPEN":
                    data.answerText = $scope.openended_answer
                    break;
                case "SINGLECLOSED":
                    data.closeEndedAnswerDTOs = [{
                        id: $scope.model.closeended_answer
                    }];
                    break;
                case "MULTIPLECLOSED":
                    data.closeEndedAnswerDTOs = [];
                    for (let answer in $scope.questions[$scope.questionIndex].closeEndedAnswerDTOSet) {
                        if ($scope.options[answer] && $scope.options[answer] === true)
                            data.closeEndedAnswerDTOs.push({
                                id: $scope.questions[$scope.questionIndex].closeEndedAnswerDTOSet[answer].id
                            })
                    }
            }

            $http.patch("/api/modifyAnswer", data).then(function onFulfilled() {

                $scope.skipQuestion();
            }, function errorCallback(response) {

                alert("Error");
                console.error(response);
            });
        }

        $scope.delete = function() {

            $http.delete("/api/deleteAnswer/" + $scope.currentAnswer.id).then(function onFulfilled() {

                $scope.skipQuestion();
            }, function errorCallback(response) {

                alert("Error");
                console.error(response);
            });
        }
    }
});
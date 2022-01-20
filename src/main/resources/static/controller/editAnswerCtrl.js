app.controller('editAnswerCtrl', function ($scope, $http) {

    $scope.model = {};
    $scope.surveys = [];
    $scope.questions = [];
    $scope.answers = [];
    $scope.currentAnswer = {};

    $scope.load = function() {

        $http.get("/api/findAllSurveys/").then(function onfulFilled(response) {

            $scope.surveys = response.data;
        }, function errorCallback(response) {

            alert("Error");
            console.log(response);
        });
    }

    $scope.selectSurvey = function(index) {

        $http.get("/api/findQuestionForSurvey/" + $scope.surveys[index].id).then(function onfulFilled(response) {

            $scope.questions = response.data;
        }, function errorCallback(response) {

            if (response.status === 404) {
                alert("Survey not found.");
            } else
            console.log(response);
        });
        $http.get("/api/findSurveyAnswersForUser/?surveyId=" + $scope.surveys[index].id + "&userId=1")
            .then(function onfulFilled(response) {

                $scope.answers = response.data;
            }, function errorCallback(response) {

                if (response.status === 404) {
                    alert("You have not compiled this survey yet.");
                } else
                    alert("Error");
                console.log(response);
            });
        $scope.question_select = undefined;
    }

    $scope.selectQuestion = function(index) {

        for (let answer of $scope.answers) {
            if (answer.questionDTO.id === $scope.questions[index].id) {
                $scope.currentAnswer = answer;
                if (answer.closeEndedAnswerDTOs)
                    $scope.model.closeended_answer =  answer.closeEndedAnswerDTOs[0].id;
            }
        }
    }

    $scope.submit = function(index) {

        let data = {
            id: $scope.currentAnswer.id,
            answerText: null,
            closeEndedAnswerDTOs: null,
            userDTO: {
                id: 1
            },
            surveyDTO: {
                id: $scope.surveys[$scope.survey_select].id
            },
            questionDTO: {
                id: $scope.questions[index].id
            }
        }

        if ($scope.questions[index].closeEndedAnswerDTOSet.length === 0) {
            data.answerText = $scope.openended_answer
        } else {
            data.closeEndedAnswerDTOs = [{
                id: $scope.model.closeended_answer
            }];
        }

        $http.patch("/api/modifyAnswer", data).then(function onfulFilled(response) {

            console.log(response.data.response);
        }, function errorCallback(response) {

            alert("Error");
            console.log(response);
        });
    }

    $scope.delete = function() {

        $http.delete("/api/deleteAnswer/" + $scope.currentAnswer.id).then(function onfulFilled(response) {

            console.log(response.data.response);
        }, function errorCallback(response) {

            alert("Error");
            console.log(response);
        });
    }
});
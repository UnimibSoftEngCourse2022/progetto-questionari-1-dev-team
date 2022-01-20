app.controller('editAnswerCtrl', function ($scope, $http) {

    $scope.model = {};
    $scope.options = [];
    $scope.surveys = [];
    $scope.questions = [];
    $scope.answers = [];
    $scope.currentAnswer = {};

    $scope.load = function() {

        $http.get("/api/findAllSurveys/").then(function onfulFilled(response) {

            $scope.surveys = response.data;
        }, function errorCallback(response) {

            if (response.status === 404) {
                alert("No survey found.");
            } else
                console.log(response);
        });
    }

    $scope.selectSurvey = function(index) {

        $http.get("/api/findQuestionForSurvey/" + $scope.surveys[index].id).then(function onfulFilled(response) {

            $scope.questions = response.data;
        }, function errorCallback(response) {

            if (response.status === 404) {
                alert("No questions found.");
            } else {
                alert("Error");
                console.error(response);
            }
        });
        $http.get("/api/findSurveyAnswersForUser/?surveyId=" + $scope.surveys[index].id + "&userId=1")
            .then(function onfulFilled(response) {

                $scope.answers = response.data;
            }, function errorCallback(response) {

                if (response.status === 404) {
                    alert("You have not compiled this survey yet.");
                } else {
                    alert("Error");
                    console.error(response);
                }
            });
        $scope.question_select = undefined;
    }

    $scope.selectQuestion = function(index) {

        for (let answer of $scope.answers) {
            if (answer.questionDTO.id === $scope.questions[index].id) {
                $scope.currentAnswer = answer;
                if ($scope.questions[index].questionType === "SINGLECLOSED")
                    $scope.model.closeended_answer =  answer.closeEndedAnswerDTOs[0].id;
                else if ($scope.questions[index].questionType === "MULTIPLECLOSED") {
                    for (let closeEndedAnswer in $scope.questions[index].closeEndedAnswerDTOSet)
                        if (answer.closeEndedAnswerDTOs
                            .find(v => v.id === $scope.questions[index].closeEndedAnswerDTOSet[closeEndedAnswer].id))
                            $scope.options[closeEndedAnswer] = true;
                }
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

        switch($scope.questions[index].questionType) {
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
                for (let answer in $scope.questions[index].closeEndedAnswerDTOSet) {
                    if ($scope.options[answer] && $scope.options[answer] === true)
                        data.closeEndedAnswerDTOs.push({
                            id: $scope.questions[index].closeEndedAnswerDTOSet[answer].id
                        })
                }
        }

        $http.patch("/api/modifyAnswer", data).then(function onfulFilled(response) {

            console.log(response.data.response);
        }, function errorCallback(response) {

            alert("Error");
            console.error(response);
        });
    }

    $scope.delete = function() {

        $http.delete("/api/deleteAnswer/" + $scope.currentAnswer.id).then(function onfulFilled(response) {

            console.log(response.data.response);
        }, function errorCallback(response) {

            alert("Error");
            console.error(response);
        });
    }
});
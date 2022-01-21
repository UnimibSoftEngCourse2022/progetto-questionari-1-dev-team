app.controller('addAnswerCtrl', function ($scope, $http, $location, routeService) {

    $scope.model = {};
    $scope.options = [];
    $scope.survey = {}
    $scope.questions = []

    $scope.load = function() {

        let surveyId = routeService.get();

        $http.head("/api/findSurveyAnswersForUser?surveyId=" + surveyId + "&userId=1")
            .then(function onfulFilled() {

                $location.path("edit_answer");
        });

        $http.get("/api/findSurvey?id=" + surveyId).then(function onfulFilled(response) {

            $scope.survey = response.data;
        }, function errorCallback(response) {

            if (response.status === 404) {
                alert("Survey not found.");
            } else {
                alert("Error");
                console.error(response);
            }
        });

        $http.get("/api/findQuestionForSurvey/" + surveyId).then(function onfulFilled(response) {

            $scope.questions = response.data;
        }, function errorCallback(response) {

            if (response.status === 404) {
                alert("No questions found.");
            } else {
                alert("Error");
                console.error(response);
            }
        });
    }

    $scope.submit = function(index) {

        let data = {
            answerText: null,
            closeEndedAnswerDTOs: null,
            userDTO: {
                id: 1
            },
            surveyDTO: {
                id: $scope.survey.id
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

        $http.post("/api/addAnswer", data).then(function onfulFilled(response) {

            alert("Answer created.");
            console.log(response.data.response);
        }, function errorCallback(response) {

            alert("Error");
            console.error(response);
        });
    }
});
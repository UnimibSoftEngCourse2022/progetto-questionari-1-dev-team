app.controller('addAnswerCtrl', function ($scope, $http) {

    $scope.model = {};
    $scope.options = [];
    $scope.surveys = []
    $scope.questions = []

    $scope.load = function() {

        $http.get("/api/findAllSurveys/").then(function onfulFilled(response) {

            $scope.surveys = response.data;
        }, function errorCallback(response) {

            if (response.status === 404) {
                alert("No survey found.");
            } else {
                alert("Error");
                console.error(response);
            }
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
    }

    $scope.submit = function(index) {

        let data = {
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

        $http.post("/api/addAnswer", data).then(function onfulFilled(response) {

            console.log(response.data.response);
        }, function errorCallback(response) {

            alert("Error");
            console.error(response);
        });
    }
});
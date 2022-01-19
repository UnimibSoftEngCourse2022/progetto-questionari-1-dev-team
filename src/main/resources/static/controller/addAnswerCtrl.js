app.controller('addAnswerCtrl', function ($scope, $http) {

    $scope.model = {};
    $scope.surveys = []
    $scope.questions = []

    $scope.load = function() {

        $http.get("/api/findAllSurveys/").then(function onfulFilled(response) {

            $scope.surveys = response.data;
        }, function errorCallback(response) {
            console.log("Error");
        });
    }

    $scope.selectSurvey = function(index) {

        $http.get("/api/findQuestionForSurvey/" + $scope.surveys[index].id).then(function onfulFilled(response) {

            $scope.questions = response.data;
        }, function errorCallback(response) {
            console.log("Error");
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
                id: $scope.questions[index].id,
                text: "",
                urlImage: ""
            }
        }

        if ($scope.questions[index].closeEndedAnswerDTOSet.length === 0) {
            data.answerText = $scope.openended_answer
        } else {
            console.log($scope.model.closeended_answer);
            data.closeEndedAnswerDTOs = [{
                id: $scope.model.closeended_answer
            }];
        }

        $http.post("/api/addAnswer", data).then(function onfulFilled(response) {

            console.log(response.data.response);
        }, function errorCallback(response) {

            console.log(response);
        });
    }
});
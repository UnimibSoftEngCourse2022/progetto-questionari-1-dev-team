app.controller('searchSurveyCtrl', function($scope, $http) {
    $scope.questions = []
    $scope.questionId = {}
    $scope.search = function(name) {
        $http.get("api/findSurvey?id="+name).then(function onFulfilled(response) {
            $scope.questionId = response.data;
        }, function errorCallback(response) {
            $scope.questionId = {};
        });
        $http.get("api/findSurveyByText/"+name).then(function onFulfilled(response) {
            $scope.questions = response.data;
        }, function errorCallback(response) {
            $scope.questions = [];
        });
        if (!$scope.questionId && !$scope.questions) {
            alert("No questions found.");
        }
    };
});
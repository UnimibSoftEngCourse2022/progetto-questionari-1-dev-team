app.controller('searchQuestionCtrl', function($scope, $http) {
    $scope.questions = []
    $scope.search = function(name) {
        $http.get("api/findQuestionsByText/"+name).then(function onFulfilled(response) {
            $scope.questions= response.data;
        }, function errorCallback(response) {

            if (response.status === 404) {
                alert("No questions found.");
            } else {
                alert("Error");
                console.error(response);
            }
        });
    };
});
app.controller('selectQuestionCtrl', function($scope, $http, $window) {

    $http.get("http://localhost:5000/api/getQuestion")
        .then(function(response) {
            $scope.questions =  response.data;
        });

    $scope.editQuestion = function (value) {
        return $window.open("http://localhost:5000/editQuestion", "_self");
    }
});
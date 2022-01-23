app.controller('signUpUserCtrl', function($scope, $http) {

    $scope.username = "";
    $scope.name = "";
    $scope.surname = "";
    $scope.email = "";
    $scope.password = "";

    $scope.signUpUser = function() {

        let data = {

            username : $scope.username,
            name : $scope.name,
            surname : $scope.surname,
            email : $scope.email,
            password : $scope.password,
            questionsCreatedDTO : null,
            surveysCreatedDTO : null

        }

        console.log(data);

        $http.post("/api/signUpUser", data).then(function onFulfilled(response) {

            console.log(response);

        }, function errorCallback(response) {

            console.error(response);
        });
    }
});


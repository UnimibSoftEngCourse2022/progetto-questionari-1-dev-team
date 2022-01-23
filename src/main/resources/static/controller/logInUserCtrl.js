app.controller('logInUserCtrl', function($scope, $http) {

    $scope.credentials.username = "";
    $scope.credentials.password = "";

    $scope.loginUser = function() {

        let data = {

            username : $scope.credentials.username,
            password : $scope.credentials.password

        }

        $http.post("/api/logInUser", data).then(function onFulfilled(response) {

            console.log(response);

        }, function errorCallback(response) {

            console.error(response);
        });
    }
});


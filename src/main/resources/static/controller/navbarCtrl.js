app.controller('logoutUserCtrl', function ($scope) {

    $scope.logoutUser = function () {
        if (authService.isLoggedIn()) {
            authService.setUser(undefined);
            alert("You have just logged out!");
        }
    }
});
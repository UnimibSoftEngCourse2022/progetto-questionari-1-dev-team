app.controller('cookiesController', function ($scope, $cookies) {

    $scope.myCookieVal = $cookies.get('cookie');
    $scope.setCookie = function (val) {
        $cookies.put('cookie', val);
    }

});
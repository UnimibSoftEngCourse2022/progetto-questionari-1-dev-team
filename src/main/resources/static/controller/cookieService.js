app.service('cookieService', ['$cookies', function ($cookies) {

    return {
        setCookie: function (val) {
            $cookies.put('userId', val);
        },
        getCookie: function () {
            return $cookies.get('userId');
        }
    }
}]);
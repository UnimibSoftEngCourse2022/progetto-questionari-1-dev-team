app.service('cookieService', ['$cookies', function ($cookies) {

    return {
        setCookie: function (key, val) {
            $cookies.put(key, val);
        },
        getCookie: function (key) {
            return $cookies.get(key);
        },
        removeCookie: function (key) {
            $cookies.remove(key);
        }
    }
}]);
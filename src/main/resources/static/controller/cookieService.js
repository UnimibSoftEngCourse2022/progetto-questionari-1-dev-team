app.service('cookieService', function () {

    let userId;

    return {
        setCookie: function (val) {
            $cookies.put(userId, val);
        },
        getCookie: function () {
            return $cookies.get(userId);
        }
    }
});
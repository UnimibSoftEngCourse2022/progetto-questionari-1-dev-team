app.service("routeService", function() {

    let serviceData = {};

    function set(data) {

        serviceData = data;
    }

    function get() {

        return serviceData;
    }

    return {
        set: set,
        get: get
    }
});